package no.niths.application.rest.school;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.RESTConstants;
import no.niths.application.rest.exception.QRCodeException;
import no.niths.application.rest.helper.QRCodeDecoder;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.school.FadderGroupList;
import no.niths.application.rest.lists.school.StudentList;
import no.niths.application.rest.school.interfaces.FadderGroupController;
import no.niths.common.constants.DomainConstantNames;
import no.niths.common.constants.SecurityConstants;
import no.niths.common.helpers.LazyFixer;
import no.niths.common.helpers.ValidationHelper;
import no.niths.domain.school.FadderGroup;
import no.niths.domain.school.Student;
import no.niths.services.interfaces.GenericService;
import no.niths.services.school.interfaces.FadderGroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Controller for FadderGroup
 * has the basic CRUD methods and
 * methods too add and remove leader, child
 * and children and remove all leaders and children
 * in addition too methods for getAllStudentsNotInAGroup,
 * getAllStudents and scanImage
 *
 * For the URL too get FadderGroup add /fadder
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
@Controller
@RequestMapping(DomainConstantNames.FADDER)
public class FadderGroupControllerImpl extends
        AbstractRESTControllerImpl<FadderGroup> implements
        FadderGroupController {

    @Autowired
    private FadderGroupService service;

    private FadderGroupList fadderGroupList = new FadderGroupList();

    private StudentList studentList = new StudentList();

    private LazyFixer<Student> lazyFixertStudent = new LazyFixer<Student>();

    /**
     * {@inheritDoc}
     * 
     * Without nullifier
     */
    @Override
    public void renewList(List<FadderGroup> list) {
        getList().clear();
        getList().addAll(list);
        getList().setData(getList()); // Used for XML marshaling
        ValidationHelper.isListEmpty(getList());
    }

    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR_AND_STUDENT)
    @RequestMapping(method = RequestMethod.GET)
    public ArrayList<FadderGroup> getAll(FadderGroup domain) {
        renewList(service.getAll(domain));
        customLazyFixer();        
        return fadderGroupList;
    }

    /**
     * This method is setting fadder children to null and 
     * removes fadder leaders children so a lazy exception is avoided
     */
    private void customLazyFixer() {
        for (FadderGroup fg : fadderGroupList) {
            fg.setFadderChildren(null);
            lazyFixertStudent.clearRelations(fg.getLeaders());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_SR_FADDER_LEADER)
    @RequestMapping(method = RequestMethod.POST)
    public FadderGroup create(@RequestBody FadderGroup domain, HttpServletResponse res) {
        return super.create(domain, res);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_SR_FADDER_LEADER)
    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody FadderGroup domain) {
        super.update(domain);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_SR_FADDER_LEADER)
    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        super.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenericService<FadderGroup> getService() {
        return service;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListAdapter<FadderGroup> getList() {
        return fadderGroupList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_SR_FADDER_LEADER)
    @RequestMapping(
            value  = "{groupId}/leader/{studentId}",
            method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK, reason = "Leader added")
    public void addLeader(
            @PathVariable Long groupId,
            @PathVariable Long studentId) {
        service.addLeader(groupId, studentId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_SR_FADDER_LEADER)
    @RequestMapping(
            value  = "{groupId}/leader/{studentId}",
            method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK, reason = "Leader removed")
    public void removeLeader(
            @PathVariable Long groupId,
            @PathVariable Long studentId) {
        service.removeLeader(groupId, studentId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_SR_FADDER_LEADER)
    @RequestMapping(
            value  = "{groupId}/child/{studentId}",
            method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK, reason = "Child added")
    public void addChild(
            @PathVariable Long groupId,
            @PathVariable Long studentId) {
        service.addChild(groupId, studentId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_SR_FADDER_LEADER)
    @RequestMapping(
            value  = { "{groupId}/children/{studentIds}" },
            method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK, reason = "Children added")
    public void addChildren(
            @PathVariable Long groupId,
            @PathVariable Long[] studentIds) {
        service.addChildren(groupId, studentIds);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_SR_FADDER_LEADER)
    @RequestMapping(
            value  = "{groupId}/child/{studentId}",
            method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK, reason = "Child removed")
    public void removeChild(
            @PathVariable Long groupId,
            @PathVariable Long studentId) {
        service.removeChild(groupId, studentId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_SR_FADDER_LEADER)
    @RequestMapping(
            value  = { "{groupId}/children/{studentIds}" },
            method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK, reason = "Children removed")
    public void removeChildren(
            @PathVariable Long groupId,
            @PathVariable Long[] studentIds) {
        service.removeChildren(groupId, studentIds);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_SR_FADDER_LEADER)
    @RequestMapping(
            value  = "{groupId}/children",
            method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK, reason = "All children removed")
    public void removeAllChildren(@PathVariable Long groupId) {
        service.removeAllChildren(groupId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    @RequestMapping(
            value  = "{groupId}/leaders",
            method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK, reason = "All leaders removed")
    public void removeAllLeaders(@PathVariable Long groupId) {
        service.removeAllLeaders(groupId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR_AND_STUDENT)
    @RequestMapping(value = "{groupId}/children", 
                    method = RequestMethod.GET, 
                    headers = RESTConstants.ACCEPT_HEADER)
    @ResponseBody
    public List<Student> getAllStudents(@PathVariable Long groupId) {

        // Clear the list as it is never newed up more than once.
        studentList.clear();

        FadderGroup fadderGroup = getGroup(groupId);

        // Adds the current FadderGroups children to the list.
        studentList.addAll(fadderGroup.getFadderChildren());
        studentList.setData(studentList); // for XML marshalling
        ValidationHelper.isListEmpty(studentList);

        lazyFixertStudent.clearRelations(studentList);

        return studentList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR_AND_STUDENT)
    @RequestMapping(value = "groupless", 
                    method = RequestMethod.GET, 
                    headers = RESTConstants.ACCEPT_HEADER)
    @ResponseBody
    public List<Student> getAllStudentsNotInAGroup() {
        
        // Clear the list as it is never newed up more than once.
        studentList.clear();
        
        // Adds the current students to the list.
        studentList.addAll(service.getStudentsNotInAGroup());
        studentList.setData(studentList); // for XML marshalling
        ValidationHelper.isListEmpty(studentList);

        lazyFixertStudent.clearRelations(studentList);

        return studentList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(
            value  = "scan-qr-code/{studentId}",
            method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK, reason = "Added to group")
    public void scanImage(
            @PathVariable Long studentId,
            HttpServletRequest req,
            HttpServletResponse response)
                    throws QRCodeException {

        if (req instanceof MultipartHttpServletRequest) {
            Map<String, MultipartFile> files =
                    ((MultipartHttpServletRequest) req)
                    .getFileMap();

            // Iterate over maps like a boss
            for (Map.Entry<String, MultipartFile> entry : files.entrySet()) {

                CommonsMultipartFile file = (CommonsMultipartFile) entry
                        .getValue();

                Long groupId = new QRCodeDecoder().decodeFadderGroupQRCode(
                        file.getBytes());

                // Add the student to the group,
                // will throw exceptions if group/student does not exist
                // or student is already added
                service.addChild(groupId, studentId);
                response.setHeader("group", groupId + "");
            }
        }
    }

    @ExceptionHandler(QRCodeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Scan failed")
    public void catchNotFoundException(QRCodeException e,
                                       HttpServletResponse response) {
        response.setHeader(ERROR, e.getMessage());
    }

    private FadderGroup getGroup(Long groupId) {
        FadderGroup group = super.getById(groupId);
        ValidationHelper.isObjectNull(group, FadderGroup.class);
        return group;
    }
}
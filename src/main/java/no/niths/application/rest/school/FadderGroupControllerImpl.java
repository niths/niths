package no.niths.application.rest.school;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.exception.QRCodeException;
import no.niths.application.rest.helper.QRCodeDecoder;
import no.niths.application.rest.lists.FadderGroupList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.StudentList;
import no.niths.application.rest.school.interfaces.FadderGroupController;
import no.niths.common.AppNames;
import no.niths.common.LazyFixer;
import no.niths.common.SecurityConstants;
import no.niths.common.ValidationHelper;
import no.niths.domain.school.FadderGroup;
import no.niths.domain.school.Student;
import no.niths.services.interfaces.GenericService;
import no.niths.services.school.interfaces.FadderGroupService;
import no.niths.services.school.interfaces.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Controller for subjects
 * 
 */
@Controller
@RequestMapping(AppNames.FADDER)
public class FadderGroupControllerImpl extends
		AbstractRESTControllerImpl<FadderGroup> implements
		FadderGroupController {

	private static final Logger logger = LoggerFactory
			.getLogger(FadderGroupControllerImpl.class);

	@Autowired
	private FadderGroupService service;

	@Autowired
	private StudentService studService;

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
	public void create(@RequestBody FadderGroup domain, HttpServletResponse res) {
		super.create(domain, res);
	}

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_SR_FADDER_LEADER)
    public void update(@RequestBody FadderGroup domain) {
        super.update(domain);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_SR_FADDER_LEADER)
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
            value  = "{groupId}/add/leader/{studentId}",
            method = RequestMethod.PUT)
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
            value  = "{groupId}/remove/leader/{studentId}",
            method = RequestMethod.PUT)
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
            value  = "{groupId}/add/child/{studentId}",
            method = RequestMethod.PUT)
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
            value  = "{groupId}/remove/child/{studentId}",
            method = RequestMethod.PUT)
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
            value  = { "{groupId}/remove/children/{studentIds}" },
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
            value  = "{groupId}/remove/all/children",
            method = RequestMethod.PUT)
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
            value  = "{groupId}/remove/all/leaders",
            method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "All leaders removed")
    public void removeAllLeaders(@PathVariable Long groupId) {
        service.removeAllLeaders(groupId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "{groupId}/children", method = RequestMethod.GET)
    @ResponseBody
    public List<Student> getAllStudents(@PathVariable Long groupId) {

        // Clear the list as it is never newed up more than once.
        studentList.clear();

        FadderGroup fadderGroup = getGroup(groupId);

        // Adds the current FadderGroups children to the list.
        studentList.addAll(fadderGroup.getFadderChildren());
        studentList.setData(studentList); // for XML marshalling
        ValidationHelper.isListEmpty(studentList);

        for (int i = 0; i < studentList.size(); i++) {
            studentList.get(i).setFeeds(null);
            studentList.get(i).setCommittees(null);
            studentList.get(i).setCourses(null);
        }

        return studentList;
    }

    /**
     * {@inheritDoc}
     *
     * @throws QRCodeException
     *             an exception describing what went wrong during scanning
     */
    @Override
    @RequestMapping(value = "scan-qr-code", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK, reason = "Scanned QR code")
    public void scanImage(HttpServletRequest req, HttpServletResponse response)
            throws QRCodeException {

        if (req instanceof MultipartHttpServletRequest) {
            Map<String, MultipartFile> files = ((MultipartHttpServletRequest) req)
                    .getFileMap();

            // Iterate over maps like a boss
            for (Map.Entry<String, MultipartFile> entry : files.entrySet()) {

                CommonsMultipartFile file = (CommonsMultipartFile) entry
                        .getValue();
                response.setHeader(
                        "location",
                        AppNames.FADDER
                                + '/'
                                + new QRCodeDecoder()
                                .decodeFadderGroupQRCode(file
                                        .getBytes()));
            }
        }
    }

    @ExceptionHandler(QRCodeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Scan failed")
    public void catchNotFoundException(QRCodeException e,
                                       HttpServletResponse response) {
        response.setHeader(ERROR, e.getMessage());
    }

	private Student getStudent(Long studId) {
		Student stud = studService.getById(studId);
		ValidationHelper.isObjectNull(stud, Student.class);
		return stud;
	}

	private FadderGroup getGroup(Long groupId) {
		FadderGroup group = super.getById(groupId);
		ValidationHelper.isObjectNull(group, FadderGroup.class);
		return group;
	}
}

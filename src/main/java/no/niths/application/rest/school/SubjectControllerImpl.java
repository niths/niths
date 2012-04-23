package no.niths.application.rest.school;

import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.SubjectList;
import no.niths.application.rest.school.interfaces.SubjectController;
import no.niths.common.constants.AppNames;
import no.niths.common.constants.SecurityConstants;
import no.niths.domain.school.Subject;
import no.niths.services.interfaces.GenericService;
import no.niths.services.school.interfaces.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller for subjects
 * 
 */
@Controller
@RequestMapping(AppNames.SUBJECTS)
public class SubjectControllerImpl extends AbstractRESTControllerImpl<Subject>
        implements SubjectController {

    @Autowired
    private SubjectService subjectService;

    private SubjectList subjectList = new SubjectList();

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    @RequestMapping(
            value  = "{subjectId}/tutor/{studentId}",
            method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK, reason = "Tutor added to subject")
    public void addTutor(
            @PathVariable Long subjectId,
            @PathVariable Long studentId) {
        subjectService.addTutor(subjectId, studentId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    @RequestMapping(
            value  = "{subjectId}/tutor/{studentId}",
            method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK, reason = "Tutor removed to subject")
    public void removeTutor(
            @PathVariable Long subjectId,
            @PathVariable Long studentId) {
        subjectService.removeTutor(subjectId, studentId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    @RequestMapping(
            value  = "{subjectId}/room/{roomId}",
            method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK, reason = "Room added to subject")
    public void addRoom(
            @PathVariable Long subjectId,
            @PathVariable Long roomId) {
        subjectService.addRoom(subjectId, roomId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    @RequestMapping(
            value  = "{subjectId}/room",
            method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK, reason = "Room removed from subject")
    public void removeRoom(@PathVariable Long subjectId) {
        subjectService.removeRoom(subjectId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    public void create(@RequestBody Subject domain, HttpServletResponse res) {
        super.create(domain, res);
    }

    /**
     * {@inheritDoc}
     */
    
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    public void delete(@PathVariable long id) {
        super.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    public void update(@RequestBody Subject domain) {
        super.update(domain);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenericService<Subject> getService() {
        return subjectService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListAdapter<Subject> getList() {
        return subjectList;
    }

}

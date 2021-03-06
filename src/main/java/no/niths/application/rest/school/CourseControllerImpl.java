package no.niths.application.rest.school;

import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.school.CourseList;
import no.niths.application.rest.school.interfaces.CourseController;
import no.niths.common.constants.DomainConstantNames;
import no.niths.common.constants.SecurityConstants;
import no.niths.domain.school.Course;
import no.niths.services.interfaces.GenericService;
import no.niths.services.school.interfaces.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for course
 * has the basic CRUD methods and
 * methods too add and remove subject
 * and representative
 *
 * For the URL too get Course add /courses
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
@Controller
@RequestMapping(DomainConstantNames.COURSES)
public class CourseControllerImpl extends AbstractRESTControllerImpl<Course>
        implements CourseController {

    @Autowired
    private CourseService courseService;

    private CourseList courseList = new CourseList();

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    public Course create(@RequestBody Course domain, HttpServletResponse res) {
        return super.create(domain, res);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    public void update(@RequestBody Course domain) {
        super.update(domain);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    public void delete(@PathVariable long id) {
        super.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR +
            " or (hasRole('ROLE_STUDENT') and principal.studentId == #studentId)")
    @RequestMapping(
            value  = "{courseId}/representative/{studentId}",
            method = RequestMethod.POST)
    @ResponseStatus(
            value  = HttpStatus.OK,
            reason = "Representative added to course")
    public void addRepresentative(
            @PathVariable Long courseId,
            @PathVariable Long studentId) {
        courseService.addRepresentative(courseId, studentId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR +
            " or (hasRole('ROLE_STUDENT') and principal.studentId == #studentId)")
    @RequestMapping(
            value  = "{courseId}/representative/{studentId}",
            method = RequestMethod.DELETE)
    @ResponseStatus(
            value  = HttpStatus.OK,
            reason = "Representative removed from course")
    public void removeRepresentative(
            @PathVariable Long courseId,
            @PathVariable Long studentId) {
        courseService.removeRepresentative(courseId, studentId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    @RequestMapping(
            value = "{courseId}/subject/{subjectId}",
            method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK, reason = "Subject added to course")
    public void addSubject(
            @PathVariable Long courseId,
            @PathVariable Long subjectId) {
        courseService.addSubject(courseId, subjectId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    @RequestMapping(
            value  = "{courseId}/subject/{subjectId}",
            method = RequestMethod.DELETE)
    @ResponseStatus(
            value  = HttpStatus.OK,
            reason = "Subject removed from course")
    public void removeSubject(
            @PathVariable Long courseId,
            @PathVariable Long subjectId) {
        courseService.removeSubject(courseId, subjectId);
    }

    @Override
    public GenericService<Course> getService() {
        return courseService;
    }

    @Override
    public ListAdapter<Course> getList() {
        return courseList;
    }
}
package no.niths.application.rest.school;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.RESTConstants;
import no.niths.application.rest.exception.DuplicateEntryCollectionException;
import no.niths.application.rest.exception.NotInCollectionException;
import no.niths.application.rest.lists.CourseList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.SubjectList;
import no.niths.application.rest.school.interfaces.CourseController;
import no.niths.common.AppConstants;
import no.niths.common.SecurityConstants;
import no.niths.common.ValidationHelper;
import no.niths.domain.school.Course;
import no.niths.domain.school.Student;
import no.niths.domain.school.Subject;
import no.niths.services.interfaces.GenericService;
import no.niths.services.school.interfaces.CourseService;
import no.niths.services.school.interfaces.StudentService;
import no.niths.services.school.interfaces.SubjectService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller for course
 * 
 */
@Controller
@RequestMapping(AppConstants.COURSES)
public class CourseControllerImpl extends AbstractRESTControllerImpl<Course>
        implements CourseController {

    private static final Logger logger = LoggerFactory
            .getLogger(CourseControllerImpl.class);

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    private CourseList courseList = new CourseList();

    private SubjectList subjectList = new SubjectList();

    @Override
    public Course getById(@PathVariable Long id) {
        Course course = super.getById(id);
        
        return course;
    }

    /**
     * Returns all topics inside a course
     * 
     * @param id
     *            the course id
     * @return List with subject
     */
    @Override
    @RequestMapping(value = "subject/{id}", method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
    @ResponseBody
    public List<Subject> getCourseSubjects(@PathVariable Long id) {
        Course course = courseService.getById(id);
        ValidationHelper.isObjectNull(course, Course.class);
        subjectList.clear();
        subjectList.addAll(course.getSubjects());
        subjectList.setData(course.getSubjects());
        ValidationHelper.isListEmpty(subjectList);
        return subjectList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "add/representative/{courseId}/{studentId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Representative added to course")
    public void addRepresentative(@PathVariable Long courseId,
            @PathVariable Long studentId) {

        Course c = courseService.getById(courseId);
        ValidationHelper.isObjectNull(c, Course.class);
        Student student = studentService.getById(studentId);
        ValidationHelper.isObjectNull(student, Student.class);
        if (c.getCourseRepresentatives().contains(student)) {
            throw new DuplicateEntryCollectionException(
                    "Student already a representative");
        }
        c.getCourseRepresentatives().add(student);
        courseService.update(c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "remove/representative/{courseId}/{studentId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Representative removed from course")
    public void removeRepresentative(@PathVariable Long courseId,
            @PathVariable Long studentId) {
        Course c = courseService.getById(courseId);
        ValidationHelper.isObjectNull(c, Course.class);

        boolean isRemoved = false;
        for (int i = 0; i < c.getCourseRepresentatives().size(); i++) {
            if (c.getCourseRepresentatives().get(i).getId() == studentId) {
                c.getCourseRepresentatives().remove(i);
                isRemoved = true;
            }
        }

        if (isRemoved) {
            courseService.update(c);
            logger.debug("representive removed");
        } else {
            throw new NotInCollectionException(
                    "Student not a representative for that class");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    public void create(@RequestBody Course domain, HttpServletResponse res) {
        super.create(domain, res);
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
    public void hibernateDelete(@PathVariable long id) {
        super.hibernateDelete(id);
    }

    /**
     * Adds a topic to a course
     * 
     * @param courseId
     *            the id of the course
     * @param subjectId
     *            the id of the topic to be added
     */
    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    @RequestMapping(value = { "add/subject/{courseId}/{subjectId}" }, method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Subject added to course")
    public void addSubjectToCourse(@PathVariable Long courseId,
            @PathVariable Long subjectId) {

        Course course = courseService.getById(courseId);
        ValidationHelper.isObjectNull(course, Course.class);

        Subject subject = subjectService.getById(subjectId);
        ValidationHelper.isObjectNull(subject, Subject.class);

        course.getSubjects().add(subject);
        courseService.update(course);
    }


    @Override
    @RequestMapping(value = { "remove/subject/{courseId}/{subjectId}" }, method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Subject added to course")
    public void reomveSubjectToCourse(@PathVariable Long courseId,@PathVariable Long subjectId) {
        Course course = courseService.getById(courseId);
        ValidationHelper.isObjectNull(course, Course.class);
        
        boolean isRemoved = false;
        for (int i = 0; i < course.getSubjects().size(); i++) {
            if (course.getSubjects().get(i).getId() == subjectId) {
                course.getSubjects().remove(i);
                isRemoved = true;
            }
        }

        if (isRemoved) {
            courseService.update(course);
            logger.debug("subject removed");
        } else {
            throw new NotInCollectionException(
                    "Subject is not in this course");
        }
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

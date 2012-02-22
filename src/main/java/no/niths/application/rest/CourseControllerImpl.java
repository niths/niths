package no.niths.application.rest;

import java.util.List;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.CourseController;
import no.niths.application.rest.lists.CourseList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.AppConstants;
import no.niths.domain.Course;
import no.niths.domain.Subject;
import no.niths.services.interfaces.CourseService;
import no.niths.services.interfaces.GenericService;
import no.niths.services.interfaces.SubjectService;

import org.hibernate.NonUniqueObjectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
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
public class CourseControllerImpl extends AbstractRESTControllerImpl<Course> implements CourseController{

	private static final Logger logger = LoggerFactory
			.getLogger(CourseControllerImpl.class);

	@Autowired
	private CourseService courseService;

	@Autowired
	private SubjectService subjectService;

	private CourseList courseList = new CourseList();

	/**
	 * Returns all topics inside a course
	 * 
	 * @param id
	 *            the course id
	 * @return List with subject
	 */
	@RequestMapping(value = "subject/{id}", method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public List<Subject> getCourseSubjects(@PathVariable Long id) {
		Course course = courseService.getById(id);
		if (course == null) {
			throw new ObjectNotFoundException();
		}
		return course.getSubjects();
	}

	/**
	 * Adds a topic to a course
	 * 
	 * @param courseId
	 *            the id of the course
	 * @param subjectId
	 *            the id of the topic to be added
	 */
	@RequestMapping(value = { "{courseId}/{subjectId}" }, method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Subject added to course")
	public void addSubjectToCourse(@PathVariable Long courseId,
			@PathVariable Long subjectId) {

		Course course = courseService.getById(courseId);
		if (course == null) {
			throw new ObjectNotFoundException("Did not find a course with id: "
					+ courseId);
		}

		Subject subject = subjectService.getById(subjectId);
		if (subject == null) {
			throw new ObjectNotFoundException("Did not find a topic with id: "
					+ subjectId);

		}
		course.getSubjects().add(subject);
		courseService.update(course);
	}


	/**
	 * Catches constraint violation exceptions
	 * Ex: Topic already added to course
	 */
	@ExceptionHandler(NonUniqueObjectException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Already added")
	public void notUniqueObject() {
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
package no.niths.application.rest;

import java.util.ArrayList;
import java.util.List;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.lists.CourseList;
import no.niths.common.AppConstants;
import no.niths.domain.Course;
import no.niths.domain.Topic;
import no.niths.services.CourseService;
import no.niths.services.TopicService;

import org.hibernate.NonUniqueObjectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(AppConstants.COURSES)
public class CourseController implements RESTController<Course> {

	private static final Logger logger = LoggerFactory
			.getLogger(CourseController.class);

	@Autowired
	private CourseService courseService;

	@Autowired
	private TopicService topicService;

	private CourseList courseList = new CourseList();

	/**
	 * 
	 * @param Course
	 *            The course to be created
	 */
	@Override
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED, reason = "Course created")
	public void create(@RequestBody Course course) {
		courseService.createCourse(course);
	}

	/**
	 * 
	 * @param long The course's id
	 * @return The course identified by the id
	 */
	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public Course getById(@PathVariable Long id) {
		Course c = courseService.getCourseById(id);
		if (c == null) {
			throw new ObjectNotFoundException("Did not find a course with id: "
					+ id);
		}
		return c;
	}

	/**
	 * Returns all topics inside a course
	 * 
	 * @param id
	 *            the course id
	 * @return List with topics
	 */
	@RequestMapping(value = "topics/{id}", method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public List<Topic> getCourseTopics(@PathVariable Long id) {
		Course c = courseService.getCourseById(id);
		if (c == null) {
			throw new ObjectNotFoundException("Did not find a course with id: "
					+ id);
		}
		return c.getTopics();
	}

	/**
	 * Returns a course with topics
	 * 
	 * @param name
	 *            name of course
	 * @param grade
	 *            the year 1,2 or 3
	 * @param term
	 *            fall or spring
	 * @return
	 */
	@RequestMapping(value = "{name}/{grade}/{term}", method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public Course getCourse(@PathVariable String name,
			@PathVariable Integer grade, @PathVariable String term) {
		Course c = courseService.getCourse(name, grade, term);
		if (c == null)
			throw new ObjectNotFoundException("Did not find any courses");
		return c;
	}

	/**
	 * 
	 * @return All courses
	 */
	@Override
	@RequestMapping(method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public ArrayList<Course> getAll(Course course) {

		courseList.clear();
		courseList.addAll(courseService.getAllCourses(course));
		courseList.setData(courseList);
		return courseList;

	}

	/**
	 * 
	 * @param Course
	 *            The Course to update
	 */
	@Override
	@RequestMapping(value = { "", "{id}" }, method = RequestMethod.PUT, headers = RESTConstants.CONTENT_TYPE_HEADER)
	@ResponseStatus(value = HttpStatus.OK, reason = "Course updated")
	public void update(@RequestBody Course course, @PathVariable Long id) {

		// If the ID is only provided through the URL.
		if (id != null)
			course.setId(id);

		courseService.updateCourse(course);
	}

	/**
	 * Adds a topic to a course
	 * 
	 * @param courseId
	 *            the id of the course
	 * @param topicId
	 *            the id of the topic to be added
	 */
	@RequestMapping(value = { "{courseId}/{topicId}" }, method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Topic added to course")
	public void addTopicToCourse(@PathVariable Long courseId,
			@PathVariable Long topicId) {

		Course c = courseService.getCourseById(courseId);
		if (c == null) {
			throw new ObjectNotFoundException("Did not find a course with id: "
					+ courseId);
		}

		Topic t = topicService.getTopicById(topicId);
		if (t == null) {
			throw new ObjectNotFoundException("Did not find a topic with id: "
					+ topicId);

		}
		c.getTopics().add(t);
		courseService.updateCourse(c);
	}

	/**
	 * 
	 * @param long The id of the Course to delete
	 */
	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK, reason = "Course deleted")
	public void delete(@PathVariable Long id) {
		if (!courseService.deleteCourse(id)) {
			throw new ObjectNotFoundException();
		}
	}

	/**
	 * Catches constraint violation exceptions
	 * Ex: Topic already added to course
	 */
	@ExceptionHandler(NonUniqueObjectException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Already added")
	public void notUniqueObject() {
	}
}
package no.niths.application.rest.interfaces;

import java.util.List;

import no.niths.domain.Course;
import no.niths.domain.Subject;
/**
 * Controller for course
 *
 */
public interface CourseController extends GenericRESTController<Course> {

	/**
	 * Returns a list of all subjects in a course
	 * @param id The id of the course
	 * @return List of subjects
	 */
	public List<Subject> getCourseSubjects(Long id);

	/**
	 * Adds a subject to a course
	 * 
	 * @param courseId the id of the course
	 * @param subjectId the id of the subject
	 */
	public void addSubjectToCourse(Long courseId, Long subjectId);

	/**
	 * Catches constraint violation exceptions
	 */
	public void notUniqueObject();
}

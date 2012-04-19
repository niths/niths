package no.niths.application.rest.school.interfaces;

import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.school.Course;
/**
 * Controller for course
 *
 */
public interface CourseController extends GenericRESTController<Course> {

	/**
	 * Adds a subject to a course
	 * 
	 * @param courseId the id of the course
	 * @param subjectId the id of the subject
	 */
	public void addSubject(Long courseId, Long subjectId);
	
	/**
	 * Removes a subject to a course
	 * @param courseId
	 * @param subjectId
	 */
	public void removeSubject(Long courseId, Long subjectId);
	
	/**
	 * Adds a representative to a course
	 * 
	 * @param courseId id of the course
	 * @param studentId id of the student to add as a representative
	 */
	void addRepresentative(Long courseId, Long studentId);
	
	/**
	 * Removes a representative from a course
	 * 
	 * @param courseId id of the course
	 * @param studentId id of the student to remove
	 */
	void removeRepresentative(Long courseId, Long studentId);

}

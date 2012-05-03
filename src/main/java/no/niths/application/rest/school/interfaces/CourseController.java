package no.niths.application.rest.school.interfaces;

import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.school.Course;
/**
 * Controller for course
 * has the basic CRUD methods and
 * methods too add and remove subject
 * and representative
 *
 * For the URL too get Course add /courses
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
public interface CourseController extends GenericRESTController<Course> {

	/**
	 * Adds a subject to a course
     *
     * Too add subject add /{courseId}/subject/{subjectId}
     * too the URL
     *
     * Use the POST method
	 * 
	 * @param courseId the id of the course
	 * @param subjectId the id of the subject
	 */
	public void addSubject(Long courseId, Long subjectId);
	
	/**
	 * Removes a subject to a course
     *
     * Too remove subject add /{courseId}/subject/{subjectId}
     * too the URL
     *
     * Use the DELETE method
     *
	 * @param courseId id for the course
	 * @param subjectId id for the subject too remove
	 */
	public void removeSubject(Long courseId, Long subjectId);
	
	/**
	 * Adds a representative to a course
     *
     * Too add representative add /{courseId}/representative/{studentId}
     * too the URL
     *
     * Use the POST method
	 * 
	 * @param courseId id of the course
	 * @param studentId id of the student to add as a representative
	 */
	void addRepresentative(Long courseId, Long studentId);
	
	/**
	 * Removes a representative from a course
     *
     * Too remove representative add /{courseId}/representative/{studentId}
     * too the URL
     *
     * Use the DELETE method
	 * 
	 * @param courseId id of the course
	 * @param studentId id of the student to remove
	 */
	void removeRepresentative(Long courseId, Long studentId);

}

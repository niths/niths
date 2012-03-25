package no.niths.application.rest.interfaces;

import no.niths.domain.Subject;
/**
 * Controller for subjects
 *
 */
public interface SubjectController extends GenericRESTController<Subject> {

	/**
	 * Removes a tutor from a subject
	 * 
	 * @param subjectId the subject to from
	 * @param studentId the id of the tutor
	 */
	void removeTutor(Long subjectd, Long studentId);
	
	/**
	 * Adds a tutor to a subject
	 * @param subjectId
	 * @param studentId
	 */
	void addTutor(Long subjectId, Long studentId);
}

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
    void removeTutor(Long subjectId, Long studentId);

    /**
     * Adds a tutor to a subject
     * @param subjectId
     * @param studentId
     */
    void addTutor(Long subjectId, Long studentId);

    /**
     * Adds a room to a subject
     * @param subjectId the id of the subject
     * @param roomId the id of the room
     */
    void addRoom(Long subjectId, Long roomId);

    /**
     * Removes a room from a subject
     *
     * @param subjectId the id of the subject
     */
    void removeRoom(Long subjectId);
}

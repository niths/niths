package no.niths.application.rest.school.interfaces;

import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.school.Subject;
/**
 * Controller for subjects
 * has the basic CRUD methods and
 * methods too add and remove tutors
 * and rooms
 *
 * For the URL too get Subjects add /subjects
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
public interface SubjectController extends GenericRESTController<Subject> {

    /**
     * Removes a tutor from a subject
     *
     * Too remove tutor add /{subjectId}/tutor/{studentId}
     * too the URL
     *
     * Use the DELETE method
     *
     * @param subjectId the id of the subject
     * @param studentId the id of the tutor
     */
    void removeTutor(Long subjectId, Long studentId);

    /**
     * Adds a tutor to a subject
     *
     * Too add tutor add /{subjectId}/tutor/{studentId}
     * too the URL
     *
     * Use the POST method
     *
     * @param subjectId id for the subject
     * @param studentId id for the student
     */
    void addTutor(Long subjectId, Long studentId);

    /**
     * Adds a room to a subject
     *
     * Too add room add /{subjectId}/room/{roomId}
     * too the URL
     *
     * Use the POST method
     *
     * @param subjectId the id of the subject
     * @param roomId the id of the room
     */
    void addRoom(Long subjectId, Long roomId);

    /**
     * Removes a room from a subject
     *
     * Too remove room add /{subjectId}/room
     * too the URL
     *
     * Use the DELETE method
     *
     * @param subjectId the id of the subject
     */
    void removeRoom(Long subjectId);
}

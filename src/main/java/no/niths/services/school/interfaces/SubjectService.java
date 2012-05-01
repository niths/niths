package no.niths.services.school.interfaces;

import no.niths.domain.school.Subject;
import no.niths.services.interfaces.GenericService;
/**
 * Service Class for Subject
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for addTutor, removeTutor, addRoom
 * and removeRoom
 * </p>
 */
public interface SubjectService extends GenericService<Subject> {

    /**
     * Adds a tutor (Student) too a Subject
     *
     * @param subjectId id for the subject
     * @param studentId id for the student
     */
    void addTutor(Long subjectId, Long studentId);

    /**
     * Removes the tutor (Student) from the Subject
     *
     * @param subjectId id for the subject
     * @param studentId id for the student
     */
    void removeTutor(Long subjectId, Long studentId);

    /**
     * Adds a Room too a Subject
     *
     * @param subjectId id for the subject
     * @param roomId id for the room
     */
    void addRoom(Long subjectId, Long roomId);

    /**
     * Removes the Room from the Subject
     *
     * @param subjectId id for the subject
     */
    void removeRoom(Long subjectId);
}
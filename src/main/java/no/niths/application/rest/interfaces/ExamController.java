package no.niths.application.rest.interfaces;

import no.niths.domain.Exam;

/**
 * Controller for exams
 */
public interface ExamController extends GenericRESTController<Exam> {

    /**
     * Adds a room too an exam
     *
     * @param examId id of the exam
     * @param roomId if of the room
     */
    public void addRoom(Long examId, Long roomId);

    /**
     * Removes a room from an exam
     *
     * @param examId id of the exam
     * @param roomId if of the room
     */
    public void removeRoom(Long examId, Long roomId);

    /**
     * Adds a subject too an exam
     *
     * @param examId id of the exam
     * @param subjectId if of the subject
     */
    public void addSubject(Long examId, Long subjectId);

    /**
     * Removes a subject from an exam
     *
     * @param examId id of the exam
     * @param subjectId if of the subject
     */
    public void removeSubject(Long examId, Long subjectId);
}

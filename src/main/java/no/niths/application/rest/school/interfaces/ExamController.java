package no.niths.application.rest.school.interfaces;

import java.util.List;

import no.niths.application.rest.helper.TimeDTO;
import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.school.Exam;

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
     */
    public void removeSubject(Long examId);
    
    
    /**
     * Returns exams on the startTime between start and end time of the time DTO object
     * @param timeDTO
     * @return
     */
	List<Exam> getExamsBetweenDates(TimeDTO timeDTO);
}

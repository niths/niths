package no.niths.services.school.interfaces;

import java.util.GregorianCalendar;
import java.util.List;

import no.niths.domain.school.Exam;
import no.niths.services.interfaces.GenericService;
/**
 * Service Class for Exam
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for addRoom, removeRoom,
 * addSubject and removeSubject
 * </p>
 */
public interface ExamService extends GenericService<Exam> {

    /**
     * Adds a room to the exam
     * @param examId id for exam
     * @param roomId id for room
     */
    void addRoom(Long examId, Long roomId);

    /**
     * Removes a room from an exam
     * @param examId id for exam
     * @param roomId id for room
     */
    void removeRoom(Long examId, Long roomId);

    /**
     * Adds a subject to the exam
     * @param examId id for exam
     * @param subjectId id for subject
     */
    void addSubject(Long examId, Long subjectId);

    /**
     * Removes a subject from a exam
     * @param examId id for exam
     */
    void removeSubject(Long examId);
    
    /**
     * Returns exams between on the startTime between start and end time 
     * @param startTime
     * @param endTime
     * @return
     */
    List<Exam> getExamsBetweenDates(GregorianCalendar startTime, GregorianCalendar endTime);
}

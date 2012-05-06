package no.niths.application.rest.school.interfaces;

import java.util.List;

import no.niths.application.rest.helper.TimeDTO;
import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.school.Exam;
import no.niths.domain.school.constants.ExamType;

/**
 * Controller for exam
 * has the basic CRUD methods and
 * methods too add and remove room
 * and subject
 * in addition too methods for getExamsBetweenDates
 *
 * For the URL too get Exam add /exams
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
public interface ExamController extends GenericRESTController<Exam> {

    /**
     * Adds a room too an exam
     *
     * Too add room add /{examId}/room/{roomId}
     * too the URL
     *
     * Use the POST method
     *
     * @param examId id of the exam
     * @param roomId if of the room
     */
    public void addRoom(Long examId, Long roomId);

    /**
     * Removes a room from an exam
     *
     * Too remove room add /{examId}/room/{roomId}
     * too the URL
     *
     * Use the DELETE method
     *
     * @param examId id of the exam
     * @param roomId if of the room
     */
    public void removeRoom(Long examId, Long roomId);

    /**
     * Adds a subject too an exam
     *
     * Too add subject add /{examId}/subject/{subjectId}
     * too the URL
     *
     * Use the POST method
     *
     * @param examId id of the exam
     * @param subjectId if of the subject
     */
    public void addSubject(Long examId, Long subjectId);

    /**
     * Removes a subject from an exam
     *
     * Too remove subject add /{studentId}/subject
     * too the URL
     *
     * Use the DELETE method
     *
     * @param examId id of the exam
     */
    public void removeSubject(Long examId);

    /**
     * Returns exams on the startTime between start and end time of the time DTO object
     *
     * Too get exams between dates add /dates
     * too the URL
     *
     * Use the GET method
     *
     * @param timeDTO date that exam should be after
     * @return a list of exams that are after a date
     */
    List<Exam> getExamsBetweenDates(TimeDTO timeDTO);

    /**
     * Returns a list of all exam types
     * 
     * @return the list of all exam types
     */
    List<ExamType> getExamTypes();
}
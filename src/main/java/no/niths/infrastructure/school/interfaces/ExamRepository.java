package no.niths.infrastructure.school.interfaces;

import java.util.GregorianCalendar;
import java.util.List;

import no.niths.domain.school.Exam;
import no.niths.infrastructure.interfaces.GenericRepository;
/**
 * Repository class for Exam
 *
 * <p>
 * Inherits the basic CRUD actions
 * </p>
 */
public interface ExamRepository extends GenericRepository<Exam> {
    
    /**
     * 
     * @param startTime
     * @param endTime
     * @return
     */
    List<Exam> getEventsBetweenDates(GregorianCalendar startTime,
            GregorianCalendar endTime) ; 
    
}

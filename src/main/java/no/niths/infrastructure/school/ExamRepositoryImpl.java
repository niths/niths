package no.niths.infrastructure.school;

import java.util.GregorianCalendar;
import java.util.List;

import no.niths.domain.school.Exam;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.QueryGenerator;
import no.niths.infrastructure.school.interfaces.ExamRepository;

import org.springframework.stereotype.Repository;

/**
 * Repository class for Exam
 * 
 * <p>
 * Inherits the basic CRUD actions
 * </p>
 */
@Repository
public class ExamRepositoryImpl extends AbstractGenericRepositoryImpl<Exam>
        implements ExamRepository {

    private QueryGenerator<Exam> queryGen;

    public ExamRepositoryImpl() {
        super(Exam.class, new Exam());
        queryGen = new QueryGenerator<>(Exam.class);
    }

    @Override
    public List<Exam> getEventsBetweenDates(GregorianCalendar startTime,
            GregorianCalendar endTime) {
        return queryGen.getBetweenDates(startTime, endTime, getSession().getCurrentSession());
    }

}

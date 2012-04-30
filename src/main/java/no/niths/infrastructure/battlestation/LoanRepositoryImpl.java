package no.niths.infrastructure.battlestation;

import no.niths.domain.battlestation.Loan;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.battlestation.interfaces.LoanRepository;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.GregorianCalendar;
import java.util.List;
/**
 * Repository class for Loan
 *
 * <p>
 * Inherits the basic CRUD actions and has method
 * for getLoansBetweenDates
 * </p>
 */
@Repository
public class LoanRepositoryImpl extends AbstractGenericRepositoryImpl<Loan> implements LoanRepository {

    private Logger logger = LoggerFactory.getLogger(LoanRepository.class);

    public LoanRepositoryImpl() {
        super(Loan.class, new Loan());
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Loan> getLoansBetweenDates(GregorianCalendar startTime, GregorianCalendar endTime) {
        String sql = "FROM " + Loan.class.getName() + " l WHERE l.startTime";
        boolean isEndTimeNull = endTime == null;
        if (isEndTimeNull) {
            sql += " >= :startTime";
        } else {
            sql += " BETWEEN :startTime AND :endTime ORDER BY l.startTime asc";
        }

        Query query = getSession().getCurrentSession().createQuery(sql);
        query.setTimestamp("startTime", startTime.getTime());
        if(!isEndTimeNull){
            query.setTimestamp("endTime", endTime.getTime());
        }


        logger.debug(query.getQueryString());
        return query.list();
    }
}

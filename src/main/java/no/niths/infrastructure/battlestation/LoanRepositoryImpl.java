package no.niths.infrastructure.battlestation;

import java.util.GregorianCalendar;
import java.util.List;

import no.niths.domain.battlestation.Loan;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.QueryGenerator;
import no.niths.infrastructure.battlestation.interfaces.LoanRepository;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
/**
 * Repository class for Loan
 *
 * <p>
 * Inherits the basic CRUD actions and has th  method
 * for getLoansBetweenDates
 * </p>
 */
@Repository
public class LoanRepositoryImpl extends AbstractGenericRepositoryImpl<Loan> implements LoanRepository {

    private static final String END_TIME = "endTime";
	private QueryGenerator<Loan> queryGen;
    
    
    public LoanRepositoryImpl() {
        super(Loan.class, new Loan());
        queryGen= new QueryGenerator<Loan>(Loan.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Loan> getLoansBetweenDates(GregorianCalendar startTime, GregorianCalendar endTime) {      
        return queryGen.getBetweenDates(startTime, endTime, getSession().getCurrentSession());
    }
    
    
    @Override
    @SuppressWarnings("unchecked")
	public List<Loan> getExpiredLoans(){
    	Criteria crit = getSession().getCurrentSession().createCriteria(Loan.class);
    	crit.add(Restrictions.lt(END_TIME, new GregorianCalendar()));
    	return crit.list();
    }
}

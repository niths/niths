package no.niths.infrastructure.battlestation;

import java.util.GregorianCalendar;
import java.util.List;

import no.niths.domain.battlestation.Loan;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.QueryGenerator;
import no.niths.infrastructure.battlestation.interfaces.LoanRepository;

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

    private QueryGenerator<Loan> queryGen;
    
    
    public LoanRepositoryImpl() {
        super(Loan.class, new Loan());
        queryGen= new QueryGenerator<>(Loan.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Loan> getLoansBetweenDates(GregorianCalendar startTime, GregorianCalendar endTime) {      
        return queryGen.getBetweenDates(startTime, endTime, getSession().getCurrentSession());
    }
}

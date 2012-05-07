package no.niths.infrastructure.battlestation.interfaces;

import no.niths.domain.battlestation.Loan;
import no.niths.infrastructure.interfaces.GenericRepository;

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
public interface LoanRepository extends GenericRepository<Loan> {

    /**
     * Returns a list of loans on the start time between the parameters
     * start and end time
     *
     * @param startTime tells when the loan should start
     * @param endTime is in this method the upper limit for the startTime
     * @return a list of loans on start time, between the startTime and endTime provided
     */
    List<Loan> getLoansBetweenDates(GregorianCalendar startTime, GregorianCalendar endTime);

    /**
     * Returns a list with loans with endTime less than the time now
     * Returns
     * @return
     */
	List<Loan> getExpiredLoans();
}

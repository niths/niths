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
     * Returnes a list of loans with startTime and endTime
     * between startTime and endTime
     *
     * @param startTime telles when the loan should start
     * @param endTime telles when the loan should stop
     * @return a list of loans between startTime and endTime
     */
    List<Loan> getLoansBetweenDates(GregorianCalendar startTime, GregorianCalendar endTime);
}

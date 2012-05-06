package no.niths.services.battlestation.interfaces;

import no.niths.domain.battlestation.Loan;
import no.niths.services.interfaces.GenericService;

import java.util.GregorianCalendar;
import java.util.List;
/**
 * Service Class for Loan
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for getLoansBetweenDates and createLoan
 * </p>
 */
public interface LoanService extends GenericService<Loan> {

 

    /**
     * Returns a list of loans that are between two dates
     * @param startTime the date which loans should come after
     * @param endTime the date which loans should be before
     * @return a list with loans
     */
    List<Loan> getLoansBetweenDates(GregorianCalendar startTime, GregorianCalendar endTime);
    
    /**
     * Method for creating a loan.
     * The method needs a console id and a student to be
     * responsible for the loan, and a return  time is needed to 
     * @param consoleId
     * @param StudentId
     * @param endTime
     */
    void createLoan(Long consoleId, Long studentId, GregorianCalendar returnTime);

    /**
     * 
     * @param id
     */
	void putBackConsoles(long id);
}

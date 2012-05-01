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
 * for getLoansBetweenDates, addConsole,
 * removeConsole, addStudent
 * and removeStudent
 * </p>
 */
public interface LoanService extends GenericService<Loan> {

    /**
     * Adds a console to the loan
     * @param loanId the loan's id
     * @param consoleId the id which tells which console to add
     */
    void addConsole(Long loanId, Long consoleId);

    /**
     * Removes a console from the loan
     * @param loanId the loan's id
     * @param consoleId the id which tells which console to remove
     */
    void removeConsole(Long loanId, Long consoleId);

    /**
     * Adds a student to the loan
     * @param loanId the loan's id
     * @param studentId the id which tells which student to add
     */
    void addStudent(Long loanId, Long studentId);

    /**
     * Removes a student to the loan
     * @param loanId the loan's id
     */
    void removeStudent(Long loanId);

    /**
     * Returns a list of loans that are between two dates
     * @param startTime the date which loans should come after
     * @param endTime the date which loans should be before
     * @return a list with loans
     */
    List<Loan> getLoansBetweenDates(GregorianCalendar startTime, GregorianCalendar endTime);
}

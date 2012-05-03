package no.niths.application.rest.battlestation.interfaces;

import no.niths.application.rest.helper.TimeDTO;
import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.battlestation.Loan;

import java.util.List;

/**
 * Controller for loan
 * has the basic CRUD methods and
 * methods too add and remove console
 * and student
 * in addition too method for getLoansBetweenDates,
 *
 * For the URL too get Loan add /loans
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
public interface LoanController extends GenericRESTController<Loan> {

    /**
     * Adds a console too a loan
     *
     * Too add console add /{loanId}/console/{consoleId}
     * too the URL
     *
     * Use the POST method
     *
     * @param loanId id of the loan
     * @param consoleId id of the console
     */
    public void addConsole(Long loanId, Long consoleId);

    /**
     * Removes a console from a loan
     *
     * Too remove console add /{loanId}/console/{consoleId}
     * too the URL
     *
     * Use the DELETE method
     *
     * @param loanId id of the loan
     * @param consoleId if of the console
     */
    public void removeConsole(Long loanId, Long consoleId);

    /**
     * Adds a student too a loan
     *
     * Too add student add /{loanId}/student/{studentId}
     * too the URL
     *
     * Use the POST method
     *
     * @param loanId id of the loan
     * @param studentId id of the student
     */
    public void addStudent(Long loanId, Long studentId);

    /**
     * Removes a student from a loan
     *
     * Too remove student add /{loanId}/student/{studentId}
     * too the URL
     *
     * Use the DELETE method
     *
     * @param loanId id of the loan
     */
    public void removeStudent(Long loanId);

    /**
     * Returns loans between or from timeDTO's startTime or / and endTime
     *
     * Too get loans between dates add /dates
     * too the URL
     *
     * Use the GET method
     *
     * @param timeDTO date for finding loans between two dates or after one
     * @return list of loans
     */
    List<Loan> getLoansBetweenDates(TimeDTO timeDTO);
}

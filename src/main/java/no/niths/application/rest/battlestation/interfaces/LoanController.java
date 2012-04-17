package no.niths.application.rest.battlestation.interfaces;

import no.niths.application.rest.helper.TimeDTO;
import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.battlestation.Loan;

import java.util.List;

/**
 * Controller for loans
 */
public interface LoanController extends GenericRESTController<Loan> {

    /**
     * Adds a console too a loan
     *
     * @param loanId id of the loan
     * @param consoleId id of the console
     */
    public void addConsole(Long loanId, Long consoleId);

    /**
     * Removes a console from a loan
     *
     * @param loanId id of the loan
     * @param consoleId if of the console
     */
    public void removeConsole(Long loanId, Long consoleId);

    /**
     * Adds a student too a loan
     *
     * @param loanId id of the loan
     * @param studentId id of the student
     */
    public void addStudent(Long loanId, Long studentId);

    /**
     * Removes a student from a loan
     *
     * @param loanId id of the loan
     */
    public void removeStudent(Long loanId);

    /**
     * Returns loans between or from timeDTO's startTime or / and endTime
     * @param timeDTO
     * @return
     */
    List<Loan> getLoansBetweenDates(TimeDTO timeDTO);
}

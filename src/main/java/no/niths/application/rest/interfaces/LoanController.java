package no.niths.application.rest.interfaces;

import no.niths.domain.battlestation.Loan;

/**
 * Controller for loans
 */
public interface LoanController extends GenericRESTController<Loan> {

    /**
     * Adds a game too a loan
     *
     * @param loanId id of the loan
     * @param gameId id of the game
     */
    public void addGame(Long loanId, Long gameId);

    /**
     * Removes a game from a loan
     *
     * @param loanId id of the loan
     * @param gameId if of the game
     */
    public void removeGame(Long loanId, Long gameId);

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
}

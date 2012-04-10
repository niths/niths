package no.niths.application.rest.interfaces;

import no.niths.domain.Console;

/**
 * Controller for games
 */
public interface ConsoleController extends GenericRESTController<Console> {

    /**
     * Adds a game too a console
     *
     * @param consoleId id of the console
     * @param gameId if of the game
     */
    public void addGame(Long consoleId, Long gameId);

    /**
     * Removes a game from a console
     *
     * @param consoleId id of the console
     * @param gameId id of the game
     */
    public void removeGame(Long consoleId, Long gameId);

    /**
     * Adds a loaned by student too a console
     *
     * @param consoleId id of the console
     * @param loanedById id of the student
     */
    public void addLoanedBy(Long consoleId, Long loanedById);

    /**
     * Removes a loaned by student from a console
     *
     * @param consoleId id of the console
     * @param loanedById if of the student
     */
    public void removeLoanedBy(Long consoleId, Long loanedById);
}

package no.niths.application.rest.battlestation.interfaces;

import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.battlestation.Console;

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
     * Adds a loan too a console
     *
     * @param consoleId id of the console
     * @param loanId id of the loan
     */
    public void addLoan(Long consoleId, Long loanId);

    /**
     * Removes a loan from a console
     *
     * @param consoleId id of the console
     */
    public void removeLoan(Long consoleId);
}

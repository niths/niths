package no.niths.application.rest.battlestation.interfaces;

import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.battlestation.Console;

/**
 * Controller for console
 * has the basic CRUD methods and
 * methods too add and remove game
 * and loan
 *
 * For the URL too get Console add /console
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
public interface ConsoleController extends GenericRESTController<Console> {

    /**
     * Adds a game too a console
     *
     * Too add game add /{consoleId}/game/{gameId}
     * too the URL
     *
     * Use the POST method
     *
     * @param consoleId id of the console
     * @param gameId if of the game
     */
    public void addGame(Long consoleId, Long gameId);

    /**
     * Removes a game from a console
     *
     * Too remove game add /{consoleId}/game/{gameId}
     * too the URL
     *
     * Use the DELETE method
     *
     * @param consoleId id of the console
     * @param gameId id of the game
     */
    public void removeGame(Long consoleId, Long gameId);

    /**
     * Adds a loan too a console
     *
     * Too add loan add /{consoleId}/loan/{loanId}
     * too the URL
     *
     * Use the POST method
     *
     * @param consoleId id of the console
     * @param loanId id of the loan
     */
    public void addLoan(Long consoleId, Long loanId);

    /**
     * Removes a loan from a console
     *
     * Too remove loan add /{consoleId}/loan
     * too the URL
     *
     * Use the DELETE method
     *
     * @param consoleId id of the console
     */
    public void removeLoan(Long consoleId);
}

package no.niths.application.rest.interfaces;

import no.niths.domain.Game;

/**
 * Controller for games
 */
public interface GameController extends GenericRESTController<Game> {

    /**
     * Adds a console too a game
     *
     * @param gameId id of the game
     * @param consoleId id of the console
     */
    public void addConsole(Long gameId, Long consoleId);

    /**
     * Removes a console from a game
     *
     * @param gameId id of the game
     * @param consoleId if of the console
     */
    public void removeConsole(Long gameId, Long consoleId);

    /**
     * Adds a loaned by student too a game
     *
     * @param gameId id of the game
     * @param loanedById id of the student
     */
    //public void addLoanedBy(Long gameId, Long loanedById);

    /**
     * Removes a loaned by student from a game
     *
     * @param gameId id of the game
     * @param loanedById if of the student
     */
    //public void removeLoanedBy(Long gameId, Long loanedById);
}

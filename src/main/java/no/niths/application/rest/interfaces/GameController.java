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
     * @param consoleId if of the console
     */
    public void addConsole(Long gameId, Long consoleId);

    /**
     * Removes a console from a game
     *
     * @param gameId id of the game
     * @param consoleId if of the console
     */
    public void removeConsole(Long gameId, Long consoleId);
}

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
}

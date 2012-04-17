package no.niths.application.rest.battlestation.interfaces;

import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.battlestation.Game;

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
     */
    public void removeConsole(Long gameId);
}

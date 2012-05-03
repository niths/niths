package no.niths.application.rest.battlestation.interfaces;

import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.battlestation.Game;

/**
 * Controller for game
 * has the basic CRUD methods and
 * methods too add and remove console
 *
 * For the URL too get Game add /game
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
public interface GameController extends GenericRESTController<Game> {

    /**
     * Adds a console too a game
     *
     * Too add console add /{gameId}/console/{consoleId}
     * too the URL
     *
     * Use the POST method
     *
     * @param gameId id of the game
     * @param consoleId id of the console
     */
    public void addConsole(Long gameId, Long consoleId);

    /**
     * Removes a console from a game
     *
     * Too remove console add /{gameId}/console/{consoleId}
     * too the URL
     *
     * Use the DELETE method
     *
     * @param gameId id of the game
     */
    public void removeConsole(Long gameId);
}

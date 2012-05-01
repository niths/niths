package no.niths.services.battlestation.interfaces;

import no.niths.domain.battlestation.Game;
import no.niths.services.interfaces.GenericService;
/**
 * Service Class for Game
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for addConsole
 * and removeConsole
 * </p>
 */
public interface GameService extends GenericService<Game> {

    /**
     * Adds a console to the game
     * @param gameId the game's id
     * @param consoleId the id which tells which console to add
     */
    void addConsole(Long gameId, Long consoleId);

    /**
     * Removes a console from the game
     * @param gameId the game's id
     */
    void removeConsole(Long gameId);
}

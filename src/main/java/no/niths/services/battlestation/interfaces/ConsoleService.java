package no.niths.services.battlestation.interfaces;

import no.niths.domain.battlestation.Console;
import no.niths.services.interfaces.GenericService;
/**
 * Service Class for Console
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for addGame, removeGame,
 * addLoan and removeLoan
 * </p>
 */
public interface ConsoleService extends GenericService<Console> {

    /**
     * Adds a game to the console
     * @param consoleId the console id
     * @param gameId the id which tells which game to add
     */
    void addGame(Long consoleId, Long gameId);

    /**
     * Removes a game from the console
     * @param consoleId the console id
     * @param gameId the id which tells which game to remove
     */
    void removeGame(Long consoleId, Long gameId);

    /**
     * Adds a console to the loan
     * @param consoleId the console id
     * @param loanId the id which tells which loan to add
     */
    void addLoan(Long consoleId, Long loanId);

    /**
     * Removes a loan from the console
     * @param consoleId the console id
     */
    void removeLoan(Long consoleId);
}

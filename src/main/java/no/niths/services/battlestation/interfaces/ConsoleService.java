package no.niths.services.battlestation.interfaces;

import no.niths.domain.battlestation.Console;
import no.niths.services.interfaces.GenericService;

public interface ConsoleService extends GenericService<Console> {

    void addGame(Long consoleId, Long gameId);

    void removeGame(Long consoleId, Long gameId);

    void addLoan(Long consoleId, Long loanId);

    void removeLoan(Long consoleId);
}

package no.niths.services.battlestation.interfaces;

import no.niths.domain.battlestation.Game;
import no.niths.services.interfaces.GenericService;

public interface GameService extends GenericService<Game> {

    void addConsole(Long gameId, Long consoleId);

    void removeConsole(Long gameId);
}

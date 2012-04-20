package no.niths.services.battlestation;

import no.niths.application.rest.helper.Status;
import no.niths.common.MessageProvider;
import no.niths.common.ValidationHelper;
import no.niths.domain.battlestation.Console;
import no.niths.domain.battlestation.Game;
import no.niths.infrastructure.battlestation.interfaces.ConsoleRepository;
import no.niths.infrastructure.battlestation.interfaces.GameRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.battlestation.interfaces.GameService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GameServiceImpl extends AbstractGenericService<Game> implements GameService {

    private Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ConsoleRepository consoleRepository;

    public Game getById(long id) {
        Game game = gameRepository.getById(id);

        if (game != null) {
            if (game.getConsole() != null) {
                game.getConsole().getName();
            }
        }
        return game;
    }

    @Override
    public GenericRepository<Game> getRepository() {
        return gameRepository;
    }

    @Override
    public void addConsole(Long gameId, Long consoleId) {
        Game game = validate(gameRepository.getById(gameId), Game.class);
        checkIfObjectExists(game.getConsole(), consoleId, Console.class);

        Console console = consoleRepository.getById(consoleId);
        ValidationHelper.isObjectNull(console, Console.class);

        game.setConsole(console);
        logger.debug(MessageProvider.buildStatusMsg(Console.class,
                Status.UPDATED));
    }

    @Override
    public void removeConsole(Long gameId) {
        Game game = validate(gameRepository.getById(gameId), Game.class);

        boolean isRemoved = false;

        if (game.getConsole() != null) {
            game.setConsole(null);
            isRemoved = true;
        }

        checkIfIsRemoved(isRemoved, Console.class);
    }
}

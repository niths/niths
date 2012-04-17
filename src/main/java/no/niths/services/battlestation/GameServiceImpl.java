package no.niths.services.battlestation;

import no.niths.application.rest.exception.DuplicateEntryCollectionException;
import no.niths.application.rest.exception.ObjectInCollectionException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.helper.*;
import no.niths.common.MessageProvider;
import no.niths.common.ValidationHelper;
import no.niths.domain.battlestation.Console;
import no.niths.domain.battlestation.Game;
import no.niths.infrastructure.interfaces.ConsoleRepository;
import no.niths.infrastructure.interfaces.GameRepository;
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
        Game game = super.getById(gameId);
        ValidationHelper.isObjectNull(game, Game.class);

        Console console = consoleRepository.getById(consoleId);
        ValidationHelper.isObjectNull(console, Console.class);

        if (game.getConsole() == null) {
            game.setConsole(console);
            logger.debug("Console added too game");
        } else {
            throw new DuplicateEntryCollectionException(
                    "Game already has a console");
        }
    }

    @Override
    public void removeConsole(Long gameId) {
        Game game = super.getById(gameId);
        ValidationHelper.isObjectNull(game, Game.class);

        boolean isRemoved = false;

        if (game.getConsole() != null) {
            game.setConsole(null);
            isRemoved = true;
        }

        if (isRemoved) {
            game.setConsole(null);
        } else {
            logger.debug("Console not found");
            throw new ObjectNotFoundException("Console not found");
        }
    }
}

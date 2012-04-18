package no.niths.application.rest.battlestation;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.battlestation.interfaces.GameController;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.lists.GameList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.AppConstants;
import no.niths.common.ValidationHelper;
import no.niths.domain.battlestation.Console;
import no.niths.domain.battlestation.Game;
import no.niths.services.battlestation.interfaces.ConsoleService;
import no.niths.services.battlestation.interfaces.GameService;
import no.niths.services.interfaces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;

/**
 * Controller for games
 */
@Controller
@RequestMapping(AppConstants.GAMES)
public class GameControllerImpl extends AbstractRESTControllerImpl<Game> implements GameController {

    private static final Logger logger = LoggerFactory
            .getLogger(GameControllerImpl.class);

    @Autowired
    private GameService gameService;

    @Autowired
    private ConsoleService consoleService;

    private GameList gameList = new GameList();

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "{gameId}/add/console/{consoleId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Console Added")
    public void addConsole(@PathVariable Long gameId, @PathVariable Long consoleId) {
        gameService.addConsole(gameId, consoleId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "{gameId}/remove/console", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Console Removed")
    public void removeConsole(@PathVariable Long gameId) {
        gameService.removeConsole(gameId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenericService<Game> getService() {
        return gameService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListAdapter<Game> getList() {
        return gameList;
    }
}

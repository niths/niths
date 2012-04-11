package no.niths.application.rest;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.ConsoleController;
import no.niths.application.rest.lists.ConsoleList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.AppConstants;
import no.niths.common.ValidationHelper;
import no.niths.domain.Console;
import no.niths.domain.Game;
import no.niths.domain.Loan;
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
@RequestMapping(AppConstants.CONSOLES)
public class ConsoleControllerImpl extends AbstractRESTControllerImpl<Console> implements ConsoleController {

    private static final Logger logger = LoggerFactory
            .getLogger(ConsoleControllerImpl.class);

    @Autowired
    private ConsoleService consoleService;

    @Autowired
    private GameService gameService;

    @Autowired
    private LoanService loanService;

    private ConsoleList consoleList = new ConsoleList();

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<Console> getAll(Console domain) {
        consoleList = (ConsoleList) super.getAll(domain);
        clearRelations();
        return consoleList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<Console> getAll(Console domain, @PathVariable int firstResult, @PathVariable int maxResults) {
        consoleList = (ConsoleList) super.getAll(domain, firstResult, maxResults);
        clearRelations();
        return consoleList;
    }

    private void clearRelations(){
        for(Console console : consoleList){
            console.setGames(null);
            console.setLoan(null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "add/game/{consoleId}/{gameId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Game Added")
    public void addGame(@PathVariable Long consoleId, @PathVariable Long gameId) {
        Console console = consoleService.getById(consoleId);
        ValidationHelper.isObjectNull(console, "Console does not exist");

        Game game = gameService.getById(gameId);
        ValidationHelper.isObjectNull(game, "Game does not exist");

        console.getGames().add(game);
        consoleService.update(console);
        logger.debug("Console updated");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "remove/game/{consoleId}/{gameId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Game Removed")
    public void removeGame(@PathVariable Long consoleId, @PathVariable Long gameId) {

        Console console = consoleService.getById(consoleId);
        ValidationHelper.isObjectNull(console, "Console does not exist");

        boolean isRemoved = false;

        for (int i = 0; i < console.getGames().size(); i++) {
            if (console.getGames().get(i).getId() == gameId) {
                console.getGames().remove(i);
                isRemoved = true;
                break;
            }
        }

        if (isRemoved) {
            consoleService.update(console);
        } else {
            logger.debug("Game not found");
            throw new ObjectNotFoundException("Game not found");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "add/loan/{consoleId}/{loanId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Loan Added")
    public void addLoan(@PathVariable Long consoleId, @PathVariable Long loanId) {
        Console console = consoleService.getById(consoleId);
        ValidationHelper.isObjectNull(console, "Console does not exist");

        Loan loan = loanService.getById(loanId);
        ValidationHelper.isObjectNull(loan, "Loan does not exist");

        console.setLoan(loan);
        consoleService.update(console);
        logger.debug("Console updated");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "remove/loan/{consoleId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Loan Removed")
    public void removeLoan(@PathVariable Long consoleId) {
        Console console = consoleService.getById(consoleId);
        ValidationHelper.isObjectNull(console, "Console does not exist");

        boolean isRemoved = false;

        if (console.getLoan() != null) {
            console.setLoan(null);
            isRemoved = true;
        }

        if (isRemoved) {
            consoleService.update(console);
        } else {
            logger.debug("Loan not found");
            throw new ObjectNotFoundException("Loan not found");
        }
    }

    @Override
    public GenericService<Console> getService() {
        return consoleService;
    }

    @Override
    public ListAdapter<Console> getList() {
        return consoleList;
    }
}

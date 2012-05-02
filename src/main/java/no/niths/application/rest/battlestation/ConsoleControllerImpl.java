package no.niths.application.rest.battlestation;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.battlestation.interfaces.ConsoleController;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.battlestation.ConsoleList;
import no.niths.common.constants.DomainConstantNames;
import no.niths.domain.battlestation.Console;
import no.niths.services.battlestation.interfaces.ConsoleService;
import no.niths.services.interfaces.GenericService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller for games
 */
@Controller
@RequestMapping(DomainConstantNames.CONSOLES)
public class ConsoleControllerImpl extends AbstractRESTControllerImpl<Console>
		implements ConsoleController {

	@Autowired
	private ConsoleService consoleService;

	private ConsoleList consoleList = new ConsoleList();

	/**
     * Adds a game too a console
     *
     * @param consoleId id of the console
     * @param gameId if of the game
     */
	@Override
	@RequestMapping(value = "{consoleId}/game/{gameId}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK, reason = "Game Added")
	public void addGame(@PathVariable Long consoleId, @PathVariable Long gameId) {
		consoleService.addGame(consoleId, gameId);
	}

	 /**
     * Removes a game from a console
     *
     * @param consoleId id of the console
     * @param gameId id of the game
     */
	@Override
	@RequestMapping(value = "{consoleId}/game/{gameId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK, reason = "Game Removed")
	public void removeGame(@PathVariable Long consoleId,
			@PathVariable Long gameId) {
		consoleService.removeGame(consoleId, gameId);
	}

	/**
     * Adds a loan too a console
     *
     * @param consoleId id of the console
     * @param loanId id of the loan
     */
	@Override
	@RequestMapping(value = "{consoleId}/loan/{loanId}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK, reason = "Loan Added")
	public void addLoan(@PathVariable Long consoleId, @PathVariable Long loanId) {
		consoleService.addLoan(consoleId, loanId);
	}

	/**
     * Removes a loan from a console
     *
     * @param consoleId id of the console
     */
	@Override
	@RequestMapping(value = "{consoleId}/loan", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK, reason = "Loan Removed")
	public void removeLoan(@PathVariable Long consoleId) {
		consoleService.removeLoan(consoleId);
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

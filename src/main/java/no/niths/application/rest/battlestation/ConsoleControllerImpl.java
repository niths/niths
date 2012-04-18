package no.niths.application.rest.battlestation;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.battlestation.interfaces.ConsoleController;
import no.niths.application.rest.lists.ConsoleList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.AppConstants;
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
@RequestMapping(AppConstants.CONSOLES)
public class ConsoleControllerImpl extends AbstractRESTControllerImpl<Console>
		implements ConsoleController {

	@Autowired
	private ConsoleService consoleService;

	private ConsoleList consoleList = new ConsoleList();

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(value = "{consoleId}/add/game/{gameId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Game Added")
	public void addGame(@PathVariable Long consoleId, @PathVariable Long gameId) {
		consoleService.addGame(consoleId, gameId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(value = "{consoleId}/remove/game/{gameId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Game Removed")
	public void removeGame(@PathVariable Long consoleId,
			@PathVariable Long gameId) {
		consoleService.removeGame(consoleId, gameId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(value = "{consoleId}/add/loan/{loanId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Loan Added")
	public void addLoan(@PathVariable Long consoleId, @PathVariable Long loanId) {
		consoleService.addLoan(consoleId, loanId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(value = "{consoleId}/remove/loan", method = RequestMethod.PUT)
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

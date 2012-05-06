package no.niths.application.rest.battlestation;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.battlestation.interfaces.GameController;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.battlestation.GameList;
import no.niths.common.constants.DomainConstantNames;
import no.niths.common.constants.SecurityConstants;
import no.niths.domain.battlestation.Game;
import no.niths.services.battlestation.interfaces.GameService;
import no.niths.services.interfaces.GenericService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller for game
 * has the basic CRUD methods and
 * methods too add and remove console
 *
 * For the URL too get Game add /game
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
@Controller
@RequestMapping(DomainConstantNames.GAMES)
public class GameControllerImpl extends AbstractRESTControllerImpl<Game>
		implements GameController {

	@Autowired
	private GameService gameService;

	private GameList gameList = new GameList();

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	@RequestMapping(value = "{gameId}/console/{consoleId}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK, reason = "Console Added")
	public void addConsole(@PathVariable Long gameId,
			@PathVariable Long consoleId) {
		gameService.addConsole(gameId, consoleId);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	@RequestMapping(value = "{gameId}/console", method = RequestMethod.DELETE)
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

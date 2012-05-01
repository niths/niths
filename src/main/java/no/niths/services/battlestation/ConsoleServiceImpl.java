package no.niths.services.battlestation;

import no.niths.application.rest.helper.Status;
import no.niths.common.helpers.MessageProvider;
import no.niths.common.helpers.ValidationHelper;
import no.niths.domain.battlestation.Console;
import no.niths.domain.battlestation.Game;
import no.niths.domain.battlestation.Loan;
import no.niths.infrastructure.battlestation.interfaces.ConsoleRepository;
import no.niths.infrastructure.battlestation.interfaces.GameRepository;
import no.niths.infrastructure.battlestation.interfaces.LoanRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.battlestation.interfaces.ConsoleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * Service Class for Console
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for addGame, removeGame,
 * addLoan and removeLoan
 * </p>
 */
@Service
@Transactional
public class ConsoleServiceImpl extends AbstractGenericService<Console> implements ConsoleService {

    private Logger logger = LoggerFactory.getLogger(ConsoleServiceImpl.class);

    @Autowired
    private ConsoleRepository consoleRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private LoanRepository loanRepository;

    public Console getById(long id) {
        Console console = consoleRepository.getById(id);

        if (console != null) {
            console.getGames().size();

            if (console.getLoan() != null) {
                console.getLoan().getStartTime();
            }
        }
        return console;
    }

    @Override
    public GenericRepository<Console> getRepository() {
        return consoleRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addGame(Long consoleId, Long gameId) {
        Console console = validate(consoleRepository.getById(consoleId), Console.class);
        checkIfObjectIsInCollection(console.getGames(), gameId, Game.class);

        Game game = gameRepository.getById(gameId);
        ValidationHelper.isObjectNull(game, Game.class);

        console.getGames().add(game);
        logger.debug(MessageProvider.buildStatusMsg(Game.class,
                Status.UPDATED));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeGame(Long consoleId, Long gameId) {
        Console console = validate(consoleRepository.getById(consoleId), Console.class);
        checkIfIsRemoved(console.getGames().remove(new Game(gameId)),
                Game.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addLoan(Long consoleId, Long loanId) {
        Console console = validate(consoleRepository.getById(consoleId), Console.class);
        checkIfObjectExists(console.getLoan(), loanId, Loan.class);

        Loan loan = loanRepository.getById(loanId);
        ValidationHelper.isObjectNull(loan, Loan.class);

        console.setLoan(loan);
        logger.debug(MessageProvider.buildStatusMsg(Loan.class,
                Status.UPDATED));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeLoan(Long consoleId) {
        Console console = validate(consoleRepository.getById(consoleId), Console.class);

        boolean isRemoved = false;

        if (console.getLoan() != null) {
            console.setLoan(null);
            isRemoved = true;
        }

        checkIfIsRemoved(isRemoved, Loan.class);
    }
}

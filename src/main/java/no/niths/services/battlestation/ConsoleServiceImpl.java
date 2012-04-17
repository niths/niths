package no.niths.services.battlestation;

import no.niths.application.rest.exception.DuplicateEntryCollectionException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.common.ValidationHelper;
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

    @Override
    public void addGame(Long consoleId, Long gameId) {
        Console console = super.getById(consoleId);
        ValidationHelper.isObjectNull(console, Console.class);

        Game game = gameRepository.getById(gameId);
        ValidationHelper.isObjectNull(game, Game.class);

        if (!console.getGames().contains(game)) {
            console.getGames().add(game);
            logger.debug("Console updated");
        } else {
            throw new DuplicateEntryCollectionException(
                    "Game is already added to the console");
        }
    }

    @Override
    public void removeGame(Long consoleId, Long gameId) {
        Console console = super.getById(consoleId);
        ValidationHelper.isObjectNull(console, Console.class);

        boolean isRemoved = false;

        for (int i = 0; i < console.getGames().size(); i++) {
            if (console.getGames().get(i).getId() == gameId) {
                console.getGames().remove(i);
                isRemoved = true;
                break;
            }
        }

        if (isRemoved) {
            logger.debug("Game removed from console " + console.getName());
        } else {
            logger.debug("Game not found");
            throw new ObjectNotFoundException("Game not found in console");
        }
    }

    @Override
    public void addLoan(Long consoleId, Long loanId) {
        Console console = super.getById(consoleId);
        ValidationHelper.isObjectNull(console, Console.class);

        Loan loan = loanRepository.getById(loanId);
        ValidationHelper.isObjectNull(loan, Loan.class);

        if (console.getLoan() == null) {
            console.setLoan(loan);
            logger.debug("Console updated");
        } else {
            throw new DuplicateEntryCollectionException(
                    "Loan is already added to the console");
        }
    }

    @Override
    public void removeLoan(Long consoleId) {
        Console console = super.getById(consoleId);
        ValidationHelper.isObjectNull(console, Console.class);

        if (console.getLoan() != null) {
            console.setLoan(null);
        } else {
            logger.debug("Loan not found");
            throw new ObjectNotFoundException("Loan not found in console");
        }
    }
}

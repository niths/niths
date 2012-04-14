package no.niths.application.rest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.validation.ConstraintViolationException;

import no.niths.application.rest.battlestation.interfaces.ConsoleController;
import no.niths.application.rest.battlestation.interfaces.GameController;
import no.niths.application.rest.battlestation.interfaces.LoanController;
import no.niths.application.rest.exception.BadRequestException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.battlestation.Console;
import no.niths.domain.battlestation.Game;
import no.niths.domain.battlestation.Loan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.GregorianCalendar;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class ConsoleControllerTest {


    @Autowired
    private ConsoleController consoleController;

    @Autowired
    private GameController gameController;

    @Autowired
    private LoanController loanController;

    @Test(expected= BadRequestException.class)
    public void testInsertNullObject_shallThrowException() {
        Console console = new Console("X");
        consoleController.create(console);
    }

    @Test
    public void testCreateAndDelete() {
        int size = 0;

        try {
            size = consoleController.getAll(null).size();
        } catch (ObjectNotFoundException exception) {
        }

        Console console = new Console("Wii");
        consoleController.create(console);

        assertThat(size + 1, is(equalTo(consoleController.getAll(null).size())));

        consoleController.hibernateDelete(console.getId());

        int currentSize = 0;

        try {
            currentSize = consoleController.getAll(null).size();
        }catch (ObjectNotFoundException exception) {
        }

        assertThat(currentSize, is(equalTo(size)));
    }

    @Test
    public void testCreateAndDeleteOfGames() {
        Console console = new Console("Wii");
        consoleController.create(console);

        assertThat(console, is(equalTo(consoleController.getById(console.getId()))));

        Game game = new Game("Super Mario");
        Game otherGame = new Game("Halo");

        gameController.create(game);
        gameController.create(otherGame);

        consoleController.addGame(console.getId(), game.getId());
        consoleController.addGame(console.getId(), otherGame.getId());

        assertThat(2, is(equalTo(consoleController.getById(console.getId()).getGames().size())));

        consoleController.removeGame(console.getId(), game.getId());

        assertThat(1, is(equalTo(consoleController.getById(console.getId()).getGames().size())));
        assertThat(gameController.getById(otherGame.getId()).getId(), is(equalTo(consoleController.getById(console.getId()).getGames().get(0).getId())));

        consoleController.hibernateDelete(console.getId());
        gameController.hibernateDelete(game.getId());
        gameController.hibernateDelete(otherGame.getId());
    }

    @Test
    public void testCreateAndDeleteOfLoan() {
        Console console = new Console("Wii");
        consoleController.create(console);

        assertThat(console, is(equalTo(consoleController.getById(console.getId()))));

        Loan loan = new Loan(new GregorianCalendar());

        loanController.create(loan);

        consoleController.addLoan(console.getId(), loan.getId());

        assertThat(loanController.getById(loan.getId()), is(equalTo(consoleController.getById(console.getId()).getLoan())));

        consoleController.removeLoan(console.getId());

        assertThat(consoleController.getById(console.getId()).getLoan(), is(nullValue()));

        consoleController.hibernateDelete(console.getId());
        loanController.hibernateDelete(loan.getId());
    }
}

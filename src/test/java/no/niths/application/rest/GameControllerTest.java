package no.niths.application.rest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.GregorianCalendar;

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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class GameControllerTest {

    private MockHttpServletResponse res;

    @Autowired
    private GameController gameController;

    @Autowired
    private ConsoleController consoleController;

    @Autowired
    private LoanController loanController;

    @Before
    public void setUp() {
        res = new MockHttpServletResponse();
    }

    @Test(expected= BadRequestException.class)
    public void testInsertNullObject_shallThrowException() {
        Game game = new Game("X");
        gameController.create(game, res);
    }

    @Test
    public void testCreateAndDelete() {
        int size = 0;

        try {
            size = gameController.getAll(null).size();
        } catch (ObjectNotFoundException exception) {
        }

        Game game = new Game("Super Mario");
        gameController.create(game, res);

        assertThat(size + 1, is(equalTo(gameController.getAll(null).size())));

        gameController.delete(game.getId());

        int currentSize = 0;

        try {
            currentSize = gameController.getAll(null).size();
        }catch (ObjectNotFoundException exception) {
        }

        assertThat(currentSize, is(equalTo(size)));
    }

    @Test
    public void testCreateAndDeleteOfConsole() {
        Game game = new Game("Super Mario");
        gameController.create(game, res);

        assertThat(game, is(equalTo(gameController.getById(game.getId()))));

        Console console = new Console("Wii");

        consoleController.create(console, res);

        gameController.addConsole(game.getId(), console.getId());

        assertThat(consoleController.getById(console.getId()), is(equalTo(gameController.getById(game.getId()).getConsole())));

        gameController.removeConsole(game.getId());

        assertThat(gameController.getById(game.getId()).getConsole(), is(nullValue()));

        gameController.delete(game.getId());
        consoleController.delete(console.getId());
    }
}

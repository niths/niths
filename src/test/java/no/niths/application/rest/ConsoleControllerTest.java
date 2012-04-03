package no.niths.application.rest;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.ConsoleController;
import no.niths.application.rest.interfaces.GameController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Console;
import no.niths.domain.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolationException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class ConsoleControllerTest {

    private static final Logger logger = LoggerFactory
            .getLogger(ConsoleControllerTest.class);

    @Autowired
    private ConsoleController consoleController;

    @Autowired
    private GameController gameController;

    @Test(expected= ConstraintViolationException.class)
    public void testInsertNullObject_shallThrowException() {
        Console console = new Console("P");
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
    public void testCreateAndDeleteOfRooms() {
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
}

package no.niths.application.rest;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.ConsoleController;
import no.niths.application.rest.interfaces.GameController;
import no.niths.application.rest.interfaces.StudentController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Console;
import no.niths.domain.Game;
import no.niths.domain.Student;
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
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class GameControllerTest {

    private static final Logger logger = LoggerFactory
            .getLogger(GameControllerTest.class);

    @Autowired
    private GameController gameController;

    @Autowired
    private ConsoleController consoleController;

    @Autowired
    private StudentController studentController;

    @Test(expected= ConstraintViolationException.class)
    public void testInsertNullObject_shallThrowException() {
        Game game = new Game("P");
        gameController.create(game);
    }

    @Test
    public void testCreateAndDelete() {
        int size = 0;

        try {
            size = gameController.getAll(null).size();
        } catch (ObjectNotFoundException exception) {
        }

        Game game = new Game("Super Mario");
        gameController.create(game);

        assertThat(size + 1, is(equalTo(gameController.getAll(null).size())));

        gameController.hibernateDelete(game.getId());

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
        gameController.create(game);

        assertThat(game, is(equalTo(gameController.getById(game.getId()))));

        Console console = new Console("Wii");

        consoleController.create(console);

        gameController.addConsole(game.getId(), console.getId());

        assertThat(consoleController.getById(console.getId()), is(equalTo(gameController.getById(game.getId()).getConsole())));

        gameController.removeConsole(game.getId(), console.getId());

        assertThat(gameController.getById(game.getId()).getConsole(), is(nullValue()));

        gameController.hibernateDelete(game.getId());
        consoleController.hibernateDelete(console.getId());
    }

    @Test
    public void testCreateAndDeleteOfStudentLoanedBy() {
        Game game = new Game("Super Mario");
        gameController.create(game);

        assertThat(game, is(equalTo(gameController.getById(game.getId()))));

        Student loanedBy = new Student("nyMail@nith.no");
        studentController.create(loanedBy);

        gameController.addLoanedBy(game.getId(), loanedBy.getId());

        assertThat(studentController.getById(loanedBy.getId()), is(equalTo(gameController.getById(game.getId()).getLoanedBy())));

        gameController.removeLoanedBy(game.getId(), loanedBy.getId());

        assertThat(gameController.getById(game.getId()).getLoanedBy(), is(nullValue()));

        gameController.hibernateDelete(game.getId());
        studentController.hibernateDelete(loanedBy.getId());
    }
}

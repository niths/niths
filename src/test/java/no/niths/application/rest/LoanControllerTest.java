package no.niths.application.rest;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.ConsoleController;
import no.niths.application.rest.interfaces.GameController;
import no.niths.application.rest.interfaces.LoanController;
import no.niths.application.rest.interfaces.StudentController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Console;
import no.niths.domain.Game;
import no.niths.domain.Loan;
import no.niths.domain.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolationException;

import java.util.GregorianCalendar;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class LoanControllerTest {

    @Autowired
    private LoanController loanController;

    @Autowired
    private GameController gameController;

    @Autowired
    private ConsoleController consoleController;

    @Autowired
    private StudentController studentController;

    @Test
    public void testCreateAndDelete() {
        int size = 0;

        try {
            size = loanController.getAll(null).size();
        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
        }

        Loan loan = new Loan(new GregorianCalendar());
        loanController.create(loan);

        assertThat(size + 1, is(equalTo(loanController.getAll(null).size())));

        loanController.hibernateDelete(loan.getId());

        int currentSize = 0;

        try {
            currentSize = loanController.getAll(null).size();
        }catch (ObjectNotFoundException exception) {
        }

        assertThat(currentSize, is(equalTo(size)));
    }

    @Test
    public void testCreateAndDeleteOfGames() {
        Loan loan = new Loan(new GregorianCalendar());
        loanController.create(loan);

        assertThat(loan, is(equalTo(loanController.getById(loan.getId()))));

        Game game = new Game("Super Mario");
        Game otherGame = new Game("Halo");

        gameController.create(game);
        gameController.create(otherGame);

        loanController.addGame(loan.getId(), game.getId());
        loanController.addGame(loan.getId(), otherGame.getId());

        assertThat(2, is(equalTo(loanController.getById(loan.getId()).getGames().size())));

        loanController.removeGame(loan.getId(), game.getId());

        assertThat(1, is(equalTo(loanController.getById(loan.getId()).getGames().size())));
        assertThat(gameController.getById(otherGame.getId()).getId(), is(equalTo(loanController.getById(loan.getId()).getGames().get(0).getId())));

        loanController.hibernateDelete(loan.getId());
        gameController.hibernateDelete(game.getId());
        gameController.hibernateDelete(otherGame.getId());
    }

    @Test
    public void testCreateAndDeleteOfConsole() {
        Loan loan = new Loan(new GregorianCalendar());
        loanController.create(loan);

        assertThat(loan, is(equalTo(loanController.getById(loan.getId()))));

        Console console = new Console("Wii");
        Console otherConsole = new Console("Xbox");

        consoleController.create(console);
        consoleController.create(otherConsole);

        loanController.addConsole(loan.getId(), console.getId());
        loanController.addConsole(loan.getId(), otherConsole.getId());

        assertThat(2, is(equalTo(loanController.getById(loan.getId()).getConsoles().size())));

        loanController.removeConsole(loan.getId(), console.getId());

        assertThat(1, is(equalTo(loanController.getById(loan.getId()).getConsoles().size())));
        assertThat(consoleController.getById(otherConsole.getId()).getId(), is(equalTo(loanController.getById(loan.getId()).getConsoles().get(0).getId())));

        loanController.hibernateDelete(loan.getId());
        consoleController.hibernateDelete(console.getId());
        consoleController.hibernateDelete(otherConsole.getId());
    }

     @Test
    public void testCreateAndDeleteOfStudent() {
        Loan loan = new Loan(new GregorianCalendar());
        loanController.create(loan);

        assertThat(loan, is(equalTo(loanController.getById(loan.getId()))));

        Student loanedBy = new Student("nyMail@nith.no");
        studentController.create(loanedBy);

        loanController.addStudent(loan.getId(), loanedBy.getId());

        assertThat(studentController.getById(loanedBy.getId()), is(equalTo(loanController.getById(loan.getId()).getStudent())));

        loanController.removeStudent(loan.getId());

        assertThat(loanController.getById(loan.getId()).getStudent(), is(nullValue()));

        loanController.hibernateDelete(loan.getId());
        studentController.hibernateDelete(loanedBy.getId());
    }
}

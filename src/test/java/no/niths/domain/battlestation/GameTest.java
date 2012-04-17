package no.niths.domain.battlestation;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import no.niths.domain.battlestation.Console;
import no.niths.domain.battlestation.Game;
import no.niths.domain.battlestation.GameCategory;
import no.niths.domain.battlestation.Loan;
import no.niths.domain.school.Student;

import org.junit.BeforeClass;
import org.junit.Test;

public class GameTest {
    private static final String NAME = "Super Mario";
    private static final GameCategory CATEGORY = GameCategory.ACTION;

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testShouldGenerateNewGame() {
        Game game = new Game();
        game.setTitle(NAME);
        game.setCategory(CATEGORY);

        assertThat(NAME, is(equalTo(game.getTitle())));
        assertThat(CATEGORY, is(equalTo(game.getCategory())));
    }

    @Test
    public void testValidationOfCorrectGameValues() {
        Game game = new Game(NAME, CATEGORY, 1);

        Set<ConstraintViolation<Game>> constraintViolations = validator
                .validate(game);

        assertThat(0, is(equalTo(constraintViolations.size())));
    }

    @Test
    public void testValidationOfIncorrectGameValues() {
        Game game = new Game("KM", CATEGORY, 1);

        Set<ConstraintViolation<Game>> constraintViolations = validator
                .validate(game);

        constraintViolations = validator.validate(game);
        assertThat(1, is(equalTo(constraintViolations.size())));
    }

    @Test
     public void testGettingConsoleFromGame() {
        Console console = new Console();

        Game game = new Game();
        game.setConsole(console);

        assertThat(console, is(equalTo(game.getConsole())));
    }

    @Test
    public void testGettingLoanFromGame() {
        Loan loan = new Loan();

        Game game = new Game();
        //game.setLoan(loan);

        //assertThat(loan, is(equalTo(game.getLoan())));
    }

    @Test
    public void testTwoEqualGames() {
        Game game = new Game(NAME, CATEGORY, 1);

        Game equalGame = game;

        assertThat(true, is(equalTo(game.equals(equalGame))));
    }

    @Test
    public void testTwoGamesWhichIsNotEqual() {
        Game game = new Game(NAME, CATEGORY, 1);
        game.setId(1L);

        Game notEqualGame = new Game(NAME, GameCategory.ACTION, 1);
        notEqualGame.setId(2L);

        assertThat(false, is(equalTo(game.equals(notEqualGame))));
    }

    @Test
    public void testEqualsBetweenNotEqualObjects() {
        Game game = new Game(NAME, CATEGORY, 1);

        Student student = new Student();

        assertThat(false, is(equalTo(game.equals(student))));
    }
}

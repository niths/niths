package no.niths.domain.battlestation;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

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
        Game game = new Game(NAME, CATEGORY);

        Set<ConstraintViolation<Game>> constraintViolations = validator
                .validate(game);

        assertThat(0, is(equalTo(constraintViolations.size())));
    }

    @Test
    public void testValidationOfIncorrectGameValues() {
        Game game = new Game("KM", CATEGORY);

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
    public void testTwoEqualGames() {
        Game game = new Game(NAME, CATEGORY);

        Game equalGame = game;

        assertThat(true, is(equalTo(game.equals(equalGame))));
    }

    @Test
    public void testTwoGamesWhichIsNotEqual() {
        Game game = new Game(NAME, CATEGORY);
        game.setId(1L);

        Game notEqualGame = new Game(NAME, GameCategory.ACTION);
        notEqualGame.setId(2L);

        assertThat(false, is(equalTo(game.equals(notEqualGame))));
    }

    @Test
    public void testEqualsBetweenNotEqualObjects() {
        Game game = new Game(NAME, CATEGORY);

        Student student = new Student();

        assertThat(false, is(equalTo(game.equals(student))));
    }
}

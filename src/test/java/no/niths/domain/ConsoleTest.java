package no.niths.domain;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ConsoleTest {
    private static final String NAME = "Super Mario";

    private static final Logger logger = LoggerFactory
            .getLogger(ConsoleTest.class);

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testShouldGenerateNewConsole() {
        Console console = new Console();
        console.setName(NAME);

        assertThat(NAME, is(equalTo(console.getName())));
    }

    @Test
    public void testValidationOfCorrectConsoleValues() {
        Console console = new Console(NAME);

        Set<ConstraintViolation<Console>> constraintViolations = validator
                .validate(console);

        assertThat(0, is(equalTo(constraintViolations.size())));
    }

    @Test
    public void testValidationOfIncorrectConsoleValues() {
        Console console = new Console("KM");

        Set<ConstraintViolation<Console>> constraintViolations = validator
                .validate(console);

        constraintViolations = validator.validate(console);
        assertThat(1, is(equalTo(constraintViolations.size())));
    }

    @Test
    public void testGettingGameFromConsole() {
        Game game = new Game();

        List<Game> games = new ArrayList<Game>();
        games.add(game);

        Console console = new Console();
        console.setGames(games);

        assertThat(game, is(equalTo(console.getGames().get(0))));
    }

    @Test
    public void testTwoEqualConsoles() {
        Console console = new Console(NAME);

        Console equalConsole = console;

        assertThat(true, is(equalTo(console.equals(equalConsole))));
    }

    @Test
    public void testTwoConsolesWhichIsNotEqual() {
        Console console = new Console(NAME);
        console.setId(1L);

        Console notEqualConsole = new Console("Halo");
        notEqualConsole.setId(2L);

        assertThat(false, is(equalTo(console.equals(notEqualConsole))));
    }

    @Test
    public void testEqualsBetweenNotEqualObjects() {
        Console console = new Console(NAME);

        Student student = new Student();

        assertThat(false, is(equalTo(console.equals(student))));
    }
}

package no.niths.domain;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LoanTest {
    private static final GregorianCalendar LOAN_DATE = new GregorianCalendar();
    private static final GregorianCalendar RETURN_DATE = new GregorianCalendar();

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testShouldGenerateNewLoan() {
        Loan loan = new Loan();
        loan.setLoanDate(LOAN_DATE);
        loan.setReturnDate(RETURN_DATE);

        assertThat(LOAN_DATE, is(equalTo(loan.getLoanDate())));
        assertThat(RETURN_DATE, is(equalTo(loan.getReturnDate())));
    }

    @Test
    public void testValidationOfCorrectLoanValues() {
        Loan loan = new Loan(LOAN_DATE, RETURN_DATE);

        Set<ConstraintViolation<Loan>> constraintViolations = validator
                .validate(loan);

        assertThat(0, is(equalTo(constraintViolations.size())));
    }

    @Test
    public void testGettingConsoleFromLoan() {
        Console console = new Console();

        List<Console> consoles = new ArrayList<>();
        consoles.add(console);

        Loan loan = new Loan();
        loan.setConsoles(consoles);

        assertThat(consoles.size(), is(equalTo(loan.getConsoles().size())));
    }

    @Test
    public void testGettingGameFromLoan() {
        Game game = new Game();

        List<Game> games = new ArrayList<>();
        games.add(game);

        Loan loan = new Loan();
        loan.setGames(games);

        assertThat(games.size(), is(equalTo(loan.getGames().size())));
    }

    @Test
    public void testTwoEqualLoans() {
        Loan loan = new Loan(LOAN_DATE, RETURN_DATE);

        Loan equalLoan = loan;

        assertThat(true, is(equalTo(loan.equals(equalLoan))));
    }

    @Test
    public void testTwoLoansWhichIsNotEqual() {
        Loan loan = new Loan(LOAN_DATE, RETURN_DATE);
        loan.setId(1L);

        Loan notEqualLoan = new Loan(LOAN_DATE);
        notEqualLoan.setId(2L);

        assertThat(false, is(equalTo(loan.equals(notEqualLoan))));
    }

    @Test
    public void testEqualsBetweenNotEqualObjects() {
        Loan loan = new Loan(LOAN_DATE, RETURN_DATE);

        Student student = new Student();

        assertThat(false, is(equalTo(loan.equals(student))));
    }
}

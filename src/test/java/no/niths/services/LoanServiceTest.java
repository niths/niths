package no.niths.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.GregorianCalendar;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Student;
import no.niths.domain.battlestation.Console;
import no.niths.domain.battlestation.Game;
import no.niths.domain.battlestation.Loan;
import no.niths.services.battlestation.interfaces.ConsoleService;
import no.niths.services.battlestation.interfaces.GameService;
import no.niths.services.battlestation.interfaces.LoanService;
import no.niths.services.interfaces.StudentService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class LoanServiceTest {

    private GregorianCalendar loanDate = new GregorianCalendar();
    private GregorianCalendar returnDate = new GregorianCalendar();

    @Autowired
    private LoanService loanService;

    @Autowired
    private GameService gameService;

    @Autowired
    private ConsoleService consoleService;

    @Autowired
    private StudentService studentService;

    @Before
    public void setUp() {

        // Nullify
    }

    @Test
    public void testCRUD(){
        int size = loanService.getAll(null).size();

        Loan loan = new Loan(loanDate);
        loanService.create(loan);
        assertThat(size + 1, is(equalTo(loanService.getAll(null).size())));

        Loan tempLoan = loanService.getById(loan.getId());
        loanDate.set(Calendar.MILLISECOND, 0);
        returnDate.set(Calendar.MILLISECOND, 0);
        
        Calendar loanDate = tempLoan.getLoanDate();
        loanDate.set(Calendar.MILLISECOND, 0);
        assertEquals(loanDate, loanDate);
                
        tempLoan.setReturnDate(returnDate);
        loanService.update(tempLoan);

        tempLoan = loanService.getById(loan.getId());
        Calendar returnDate = tempLoan.getReturnDate();
                returnDate.set(Calendar.MILLISECOND, 0);
        assertThat(returnDate, is(equalTo(returnDate)));

        loanService.hibernateDelete(loan.getId());
        assertThat(size, is(equalTo(loanService.getAll(null).size())));
    }

    @Test
    public void testRelationsBetweenLoanAndGame(){
        Game game = new Game("Super Mario");
        Game otherGame = new Game("Halo");

        gameService.create(game);
        gameService.create(otherGame);



        Loan loan = new Loan(loanDate, returnDate);
        loanService.create(loan);

        loan.getGames().add(game);
        loan.getGames().add(otherGame);
        loanService.update(loan);

        assertThat(2, is(equalTo(loanService.getById(loan.getId()).getGames().size())));

        loanService.hibernateDelete(loan.getId());
        gameService.hibernateDelete(game.getId());
        gameService.hibernateDelete(otherGame.getId());
    }

    @Test
    public void testRelationsBetweenLoanAndConsole(){
        Console console = new Console("Wii");
        Console otherConsole = new Console("Xbox");

        consoleService.create(console);
        consoleService.create(otherConsole);



        Loan loan = new Loan(loanDate, returnDate);
        loanService.create(loan);

        loan.getConsoles().add(console);
        loan.getConsoles().add(otherConsole);
        loanService.update(loan);

        assertThat(2, is(equalTo(loanService.getById(loan.getId()).getConsoles().size())));

        loanService.hibernateDelete(loan.getId());
        consoleService.hibernateDelete(console.getId());
        consoleService.hibernateDelete(otherConsole.getId());
    }

    @Test
    public void testRelationsBetweenLoanAndStudent(){
        Student student = new Student("enEmail@nith.no");
        studentService.create(student);

        Loan loan = new Loan(loanDate);
        loanService.create(loan);

        loan.setStudent(student);
        loanService.update(loan);

        assertThat(studentService.getById(student.getId()), is(equalTo(loanService.getById(loan.getId()).getStudent())));

        loanService.hibernateDelete(loan.getId());
        studentService.hibernateDelete(student.getId());
    }
}

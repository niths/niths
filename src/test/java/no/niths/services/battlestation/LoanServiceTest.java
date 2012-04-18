package no.niths.services.battlestation;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.battlestation.Console;
import no.niths.domain.battlestation.Loan;
import no.niths.domain.school.Student;
import no.niths.services.battlestation.interfaces.ConsoleService;
import no.niths.services.battlestation.interfaces.GameService;
import no.niths.services.battlestation.interfaces.LoanService;
import no.niths.services.school.interfaces.StudentService;

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
        
        Calendar loanDate = tempLoan.getStartTime();
        loanDate.set(Calendar.MILLISECOND, 0);
        assertEquals(loanDate, loanDate);
                
        tempLoan.setEndTime(returnDate);
        loanService.update(tempLoan);

        tempLoan = loanService.getById(loan.getId());
        Calendar returnDate = tempLoan.getEndTime();
                returnDate.set(Calendar.MILLISECOND, 0);
        assertThat(returnDate, is(equalTo(returnDate)));

        loanService.hibernateDelete(loan.getId());
        assertThat(size, is(equalTo(loanService.getAll(null).size())));
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

    @Test
    public void testGetLoansGreaterThanAGivenDay(){
        GregorianCalendar cal = new GregorianCalendar(2012, Calendar.DECEMBER, 23, 22, 21, 23);
        Loan loan = new Loan(cal);
        loanService.create(loan);

        List<Loan> loans = loanService.getLoansBetweenDates(cal, null);
        assertEquals(1, loans.size());

        loanService.hibernateDelete(loan.getId());
    }

    @Test
    public void testGetloansBetweenDates(){
        GregorianCalendar cal = new GregorianCalendar(2012, Calendar.MARCH, 9, 22, 21, 23);
        GregorianCalendar cal2 = new GregorianCalendar(2012, Calendar.APRIL, 25, 22, 21, 23);
        Loan loan = new Loan(cal);
        Loan otherLoan = new Loan(cal2);
        loanService.create(loan);
        loanService.create(otherLoan);

        System.out.println(cal.getTime());
        System.out.println(cal2.getTime());

        List<Loan> loans = loanService.getLoansBetweenDates(cal, cal2);
        assertEquals(2, loans.size());

        loanService.hibernateDelete(loan.getId());
        loanService.hibernateDelete(otherLoan.getId());
    }
}

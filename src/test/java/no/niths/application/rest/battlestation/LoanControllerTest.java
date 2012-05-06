package no.niths.application.rest.battlestation;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.GregorianCalendar;
import java.util.List;

import no.niths.application.rest.battlestation.interfaces.ConsoleController;
import no.niths.application.rest.battlestation.interfaces.LoanController;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.helper.TimeDTO;
import no.niths.application.rest.school.interfaces.StudentController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.battlestation.Console;
import no.niths.domain.battlestation.Loan;
import no.niths.domain.school.Student;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class LoanControllerTest {

    private MockHttpServletResponse res;

    @Autowired
    private LoanController loanController;

    @Autowired
    private ConsoleController consoleController;

    @Autowired
    private StudentController studentController;

   
    @Before
    public void setUp() {
        res = new MockHttpServletResponse();
    }

    @Test
    public void testCreateAndDelete() {
        int size = 0;

        try {
            size = loanController.getAll(null).size();
        } catch (ObjectNotFoundException e) {
            //e.printStackTrace();
        }

        Loan loan = new Loan(new GregorianCalendar());
        loanController.create(loan, res);

        assertThat(size + 1, is(equalTo(loanController.getAll(null).size())));

        loanController.delete(loan.getId());

        int currentSize = 0;

        try {
            currentSize = loanController.getAll(null).size();
        }catch (ObjectNotFoundException exception) {
        }

        assertThat(currentSize, is(equalTo(size)));
    }

    @Test
    public void testCreateAndRemoveLoan() {
    	 int size = 0;

         try {
             size = loanController.getAll(null).size();
             System.err.println(size);
         } catch (ObjectNotFoundException e) {
             //e.printStackTrace();
         }
         
        Console console = new Console("Wii");
        consoleController.create(console, res);
    
        Student student = new Student("student@nith.no");
        studentController.create(student, res);

        loanController.creatLoan(console.getId(), student.getId(), "20-06-2012-15:40");
        
        Loan l = loanController.getAll(null).get(0);
        assertEquals(size +1 , loanController.getAll(null).size());
        
        loanController.delete(l.getId());
        

        studentController.delete(        studentController.getAll(student).get(0).getId());
    }

    
   

    @Test
    public void testGetLoansBetweenDates() {
        Loan loan = new Loan(new GregorianCalendar(2012, 4, 9, 10, 55));
        loanController.create(loan, res);

        String start = "09/04/2012-10:55";
        String end = "09/05/2012-10:55";

        TimeDTO tdto = new TimeDTO(start, end);
        List<Loan> events = loanController.getLoansBetweenDates(tdto);

        assertEquals(1, events.size());
    }

    @Test
    public void testGetEventsFromADate() {
        Loan loan = new Loan(new GregorianCalendar(2012, 4, 9, 11, 55));
        loanController.create(loan, res);

        String start = "09/05/2012-11:55";
        TimeDTO tdto = new TimeDTO(start, null);
        List<Loan> events = loanController.getLoansBetweenDates(tdto);
        assertEquals(1, events.size());
    }
}

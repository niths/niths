package no.niths.infrastructure;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.battlestation.Loan;
import no.niths.infrastructure.battlestation.interfaces.LoanRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager")
public class LoanRepositoryTest {

    private static final GregorianCalendar LOAN_DATE = new GregorianCalendar();
    private static final GregorianCalendar RETURN_DATE = new GregorianCalendar();

    @Autowired
    private LoanRepository loanRepository;

    @Test(expected = IllegalArgumentException.class)
    public void whenInsertNull_persistenceShouldFail() {
        loanRepository.create(null);
    }

    @Test
    public void whenLoanIsCreated_LoanShouldBePersisted() {
        int size = loanRepository.getAll(null).size();

        Loan loan = new Loan();
        loan.setStartTime(LOAN_DATE);
        loanRepository.create(loan);

        assertThat(size + 1, is(equalTo(loanRepository.getAll(null).size())));
    }

    @Test
    public void whenGetById_LoanShouldBeReturned() {
        int size = loanRepository.getAll(null).size();

        Loan loan = new Loan();
        loan.setStartTime(LOAN_DATE);
        loan.setEndTime(RETURN_DATE);
        loanRepository.create(loan);

        assertThat(size + 1, is(equalTo(loanRepository.getAll(null).size())));

        Loan result = loanRepository.getById(loan.getId());
        assertThat(result, is(equalTo(loan)));

        result = loanRepository.getById(999L);
        assertThat(result, is(equalTo(null)));
    }

    @Test
    public void whenLoanIsUpdated_LoanShouldBeUpdated() {
        int size = loanRepository.getAll(null).size();

        Loan loan = new Loan();
        loan.setStartTime(LOAN_DATE);
        loanRepository.create(loan);

        assertThat(size + 1, is(equalTo(loanRepository.getAll(null).size())));

        loan.setEndTime(RETURN_DATE);
        loanRepository.update(loan);

        assertThat(RETURN_DATE, is(equalTo(loanRepository.getById(loan.getId()).getEndTime())));
    }

    @Test
    public void whenGetAll_allShouldBeReturned() {
        int size = loanRepository.getAll(null).size();

        Loan loan = new Loan();
        loan.setStartTime(new GregorianCalendar(2011, Calendar.APRIL, 10, 15, 10));
        loanRepository.create(loan);
        Loan otherLoan = new Loan();
        otherLoan.setStartTime(new GregorianCalendar(2011, Calendar.APRIL, 10, 15, 10));
        loanRepository.create(otherLoan);
        Loan thirdLoan = new Loan();
        thirdLoan.setStartTime(new GregorianCalendar(2009, Calendar.APRIL, 10, 15, 10));
        loanRepository.create(thirdLoan);

        assertThat(size + 3, is(equalTo(loanRepository.getAll(null).size())));

        assertThat(1, is(equalTo(loanRepository.getAll(thirdLoan).size())));
    }

    @Test
    public void testGetLoansBetweenToDates(){
        GregorianCalendar startTime = new GregorianCalendar(2012, Calendar.APRIL, 10, 15, 10);
        GregorianCalendar endDate = new GregorianCalendar(2012, Calendar.APRIL, 15, 22, 20);
        GregorianCalendar oldDate = new GregorianCalendar(2012, Calendar.APRIL, 15, 22, 21);

        Loan loan = new Loan(startTime);
        Loan otherLoan = new Loan(endDate);
        Loan thirdLoan = new Loan(oldDate);
        loanRepository.create(loan);
        loanRepository.create(otherLoan);
        loanRepository.create(thirdLoan);


        List<Loan> loans = loanRepository.getLoansBetweenDates(startTime, endDate);

        assertEquals(2, loans.size());
    }


    @Test
    public void testGetEventsAfterADate(){
        GregorianCalendar startTime = new GregorianCalendar(2012, Calendar.MAY, 10, 15, 10);
        GregorianCalendar endDate = new GregorianCalendar(2012, Calendar.MAY, 15, 22, 20);
        GregorianCalendar oldDate = new GregorianCalendar(2012, Calendar.MAY, 15, 22, 21);

        Loan loan = new Loan(startTime);
        Loan otherLoan = new Loan(endDate);
        Loan thirdLoan = new Loan(oldDate);
        loanRepository.create(loan);
        loanRepository.create(otherLoan);
        loanRepository.create(thirdLoan);


        List<Loan> loans = loanRepository.getLoansBetweenDates(endDate, null);

        assertEquals(2, loans.size());
    }
}

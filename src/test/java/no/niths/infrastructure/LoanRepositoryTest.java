package no.niths.infrastructure;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Game;
import no.niths.domain.Loan;
import no.niths.infrastructure.interfaces.GameRepository;
import no.niths.infrastructure.interfaces.LoanRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.GregorianCalendar;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
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
        loan.setLoanDate(LOAN_DATE);
        loanRepository.create(loan);

        assertThat(size + 1, is(equalTo(loanRepository.getAll(null).size())));
    }

    @Test
    public void whenGetById_LoanShouldBeReturned() {
        int size = loanRepository.getAll(null).size();

        Loan loan = new Loan();
        loan.setLoanDate(LOAN_DATE);
        loan.setReturnDate(RETURN_DATE);
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
        loan.setLoanDate(LOAN_DATE);
        loanRepository.create(loan);

        assertThat(size + 1, is(equalTo(loanRepository.getAll(null).size())));

        loan.setReturnDate(RETURN_DATE);
        loanRepository.update(loan);

        assertThat(RETURN_DATE, is(equalTo(loanRepository.getById(loan.getId()).getReturnDate())));
    }

    @Test
    public void whenGetAll_allShouldBeReturned() {
        int size = loanRepository.getAll(null).size();

        Loan loan = new Loan();
        loan.setLoanDate(LOAN_DATE);
        loan.setReturnDate(RETURN_DATE);
        loanRepository.create(loan);
        Loan otherLoan = new Loan();
        otherLoan.setLoanDate(LOAN_DATE);
        loanRepository.create(otherLoan);
        Loan thirdLoan = new Loan();
        thirdLoan.setLoanDate(new GregorianCalendar());
        loanRepository.create(thirdLoan);

        assertThat(size + 3, is(equalTo(loanRepository.getAll(null).size())));

        assertThat(size + 1, is(equalTo(loanRepository.getAll(loan).size())));
    }
}

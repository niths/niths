package no.niths.services.battlestation;

import no.niths.application.rest.exception.DuplicateEntryCollectionException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.common.LazyFixer;
import no.niths.common.ValidationHelper;
import no.niths.domain.battlestation.Console;
import no.niths.domain.battlestation.Loan;
import no.niths.domain.school.Student;
import no.niths.infrastructure.interfaces.ConsoleRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.interfaces.LoanRepository;
import no.niths.infrastructure.interfaces.StudentRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.battlestation.interfaces.LoanService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.GregorianCalendar;
import java.util.List;

@Service
@Transactional
public class LoanServiceImpl extends AbstractGenericService<Loan> implements LoanService {

    private Logger logger = LoggerFactory.getLogger(LoanServiceImpl.class);

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ConsoleRepository consoleRepository;

    @Autowired
    private StudentRepository studentRepository;

    private LazyFixer<Loan> fixer = new LazyFixer<Loan>();

    public Loan getById(long id) {
        Loan loan = loanRepository.getById(id);

        if (loan != null) {
            loan.getConsoles().size();

            if (loan.getStudent() != null) {
                loan.getStudent().getFirstName();
            }
        }
        return loan;
    }

    @Override
    public GenericRepository<Loan> getRepository() {
        return loanRepository;
    }

    @Override
    public void addConsole(Long loanId, Long consoleId) {
        Loan loan = super.getById(loanId);
        ValidationHelper.isObjectNull(loan, Loan.class);

        Console console = consoleRepository.getById(consoleId);
        ValidationHelper.isObjectNull(console, Console.class);

        if (!loan.getConsoles().contains(console)) {
            loan.getConsoles().add(console);
            logger.debug("Loan updated");
        } else {
            throw new DuplicateEntryCollectionException(
                    "Console is already added to the loan");
        }
    }

    @Override
    public void removeConsole(Long loanId, Long consoleId) {
        Loan loan = super.getById(loanId);
        ValidationHelper.isObjectNull(loan, Loan.class);

        boolean isRemoved = false;

        for (int i = 0; i < loan.getConsoles().size(); i++) {
            if (loan.getConsoles().get(i).getId() == consoleId) {
                loan.getConsoles().remove(i);
                isRemoved = true;
                break;
            }
        }

        if (isRemoved) {
            logger.debug("Console removed from loan ");
        } else {
            logger.debug("Console not found");
            throw new ObjectNotFoundException("Console not found in loan");
        }
    }

    @Override
    public void addStudent(Long loanId, Long studentId) {
        Loan loan = super.getById(loanId);
        ValidationHelper.isObjectNull(loan, Loan.class);

        Student student = studentRepository.getById(studentId);
        ValidationHelper.isObjectNull(student, Student.class);

        if (loan.getStudent() == null) {
            loan.setStudent(student);
            logger.debug("Loan updated");
        } else {
            throw new DuplicateEntryCollectionException(
                    "Student is already added to loan");
        }
    }

    @Override
    public void removeStudent(Long loanId) {
        Loan loan = super.getById(loanId);
        ValidationHelper.isObjectNull(loan, Loan.class);

        if (loan.getStudent() != null) {
            loan.setStudent(null);
        } else {
            logger.debug("Student not found");
            throw new ObjectNotFoundException("Student not found in loan");
        }
    }

    @Override
    public List<Loan> getLoansBetweenDates(GregorianCalendar startTime, GregorianCalendar endTime) {
        List<Loan> loans = loanRepository.getLoansBetweenDates(startTime, endTime);
        fixer.fetchChildren(loans);
        return loans;
    }
}

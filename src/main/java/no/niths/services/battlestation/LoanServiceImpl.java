package no.niths.services.battlestation;

import java.util.GregorianCalendar;
import java.util.List;

import no.niths.application.rest.exception.LoanException;
import no.niths.common.helpers.LazyFixer;
import no.niths.common.helpers.ValidationHelper;
import no.niths.domain.battlestation.Console;
import no.niths.domain.battlestation.Loan;
import no.niths.domain.school.Student;
import no.niths.infrastructure.battlestation.interfaces.ConsoleRepository;
import no.niths.infrastructure.battlestation.interfaces.LoanRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.school.interfaces.StudentRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.battlestation.interfaces.LoanService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Class for Loan
 * 
 * <p>
 * Inherits the basic CRUD actions and has methods for getLoansBetweenDates,
 * addConsole, removeConsole, addStudent and removeStudent
 * </p>
 */
@Service
@Transactional
public class LoanServiceImpl extends AbstractGenericService<Loan> implements
		LoanService {

	private Logger logger = LoggerFactory.getLogger(LoanServiceImpl.class);

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private ConsoleRepository consoleRepository;

	@Autowired
	private StudentRepository studentRepository;

	private LazyFixer<Loan> fixer = new LazyFixer<Loan>();

	@Override
	public GenericRepository<Loan> getRepository() {
		return loanRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Loan> getLoansBetweenDates(GregorianCalendar startTime,
			GregorianCalendar endTime) {
		List<Loan> loans = loanRepository.getLoansBetweenDates(startTime,
				endTime);
		fixer.fetchChildren(loans);
		return loans;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createLoan(Long consoleId, Long studentId,
			GregorianCalendar endTime) {
		Console console = consoleRepository.getById(consoleId);
		ValidationHelper.isObjectNull(console, Console.class);

		if (console.isLoand() != null) {
			if (console.isLoand()) {
				throw new LoanException("The Console is loand try another");
			}
		}
		Student student = studentRepository.getById(studentId);
		ValidationHelper.isObjectNull(student, Student.class);

		Loan loan = new Loan(new GregorianCalendar());
		loan.setEndTime(endTime);
		loan.setStudent(student);
		loan.getConsoles().add(console);
		super.create(loan);
		console.setIsLoand(true);
		logger.debug("loan created");
	}

	@Override
	public void putBackConsoles(long id) {
		Loan l = getById(id);
		for(Console c: l.getConsoles()){
			c.setIsLoand(false);
		}	
	}
}

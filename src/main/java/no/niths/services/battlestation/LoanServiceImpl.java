package no.niths.services.battlestation;

import java.util.GregorianCalendar;
import java.util.List;

import no.niths.application.rest.exception.LoanException;
import no.niths.application.rest.helper.Status;
import no.niths.common.helpers.LazyFixer;
import no.niths.common.helpers.MessageProvider;
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

/**
 * Service Class for Loan
 * 
 * <p>
 * Inherits the basic CRUD actions and has methods for getLoansBetweenDates,
 * addConsole, removeConsole, addStudent and removeStudent
 * </p>
 */
@Service
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

		if (console.isLoaned() != null) {
			if (console.isLoaned()) {
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
		console.setIsLoaned(true);
		logger.debug("loan created");
	}

	@Override
	public void putBackConsoles(long id) {
		Loan l = getById(id);
		for (Console c : l.getConsoles()) {
			c.setIsLoaned(false);
		}
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void addConsole(Long loanId, Long consoleId) {

		Loan loan = validate(loanRepository.getById(loanId), Loan.class);
		checkIfObjectIsInCollection(loan.getConsoles(), consoleId,
				Console.class);

		Console console = consoleRepository.getById(consoleId);
		ValidationHelper.isObjectNull(console, Console.class);

		console.setIsLoaned(true);
		loan.getConsoles().add(console);
		logger.debug(MessageProvider.buildStatusMsg(Console.class,Status.UPDATED));

	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void removeConsole(Long loanId, Long consoleId) {

		Loan loan = validate(loanRepository.getById(loanId), Loan.class);

		if (loan.getConsoles().size() > 1) {
			Console console = consoleRepository.getById(consoleId);
			ValidationHelper.isObjectNull(console, Console.class);
			console.setIsLoaned(false);
			checkIfIsRemoved(loan.getConsoles().remove(new Console(consoleId)),
					Console.class);
		} else {
			throw new LoanException(
					"Only one console is registerd on the loan, so you can't remove the console from the loan.");
		}

	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void changeStudent(Long loanId, Long studentId) {
		Loan loan = validate(loanRepository.getById(loanId), Loan.class);
		checkIfObjectExists(loan.getStudent(), studentId, Student.class);
		Student student = studentRepository.getById(studentId);
		ValidationHelper.isObjectNull(student, Student.class);
		loan.setStudent(student);
		logger.debug(MessageProvider.buildStatusMsg(Student.class,
				Status.UPDATED));
	}
}

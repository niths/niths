package no.niths.application.rest.battlestation;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.battlestation.interfaces.LoanController;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.LoanList;
import no.niths.common.AppConstants;
import no.niths.common.ValidationHelper;
import no.niths.domain.Student;
import no.niths.domain.battlestation.Console;
import no.niths.domain.battlestation.Game;
import no.niths.domain.battlestation.Loan;
import no.niths.services.battlestation.interfaces.ConsoleService;
import no.niths.services.battlestation.interfaces.GameService;
import no.niths.services.battlestation.interfaces.LoanService;
import no.niths.services.interfaces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;

/**
 * Controller for loan
 */
@Controller
@RequestMapping(AppConstants.LOANS)
public class LoanControllerImpl extends AbstractRESTControllerImpl<Loan> implements LoanController{

    private static final Logger logger = LoggerFactory
            .getLogger(LoanControllerImpl.class);

    @Autowired
    private LoanService loanService;

    @Autowired
    private GameService gameService;

    @Autowired
    private ConsoleService consoleService;

    @Autowired
    private StudentService studentService;

    private LoanList loanList = new LoanList();

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<Loan> getAll(Loan domain) {
        loanList = (LoanList) super.getAll(domain);
        clearRelations();
        return loanList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<Loan> getAll(Loan domain, @PathVariable int firstResult, @PathVariable int maxResults) {
        loanList = (LoanList) super.getAll(domain, firstResult, maxResults);
        clearRelations();
        return loanList;
    }

    private void clearRelations(){
        for(Loan loan : loanList){
            loan.setConsoles(null);
            loan.setStudent(null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "remove/game/{loanId}/{gameId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Game Removed")
    public void removeGame(@PathVariable Long loanId, @PathVariable Long gameId) {
        Loan loan = loanService.getById(loanId);
        ValidationHelper.isObjectNull(loan, Loan.class);

        boolean isRemoved = false;

        if (isRemoved) {
            loanService.update(loan);
        } else {
            logger.debug("Game not found");
            throw new ObjectNotFoundException("Game not found");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "add/console/{loanId}/{consoleId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Console Added")
    public void addConsole(@PathVariable Long loanId, @PathVariable Long consoleId) {
        Loan loan = loanService.getById(loanId);
        ValidationHelper.isObjectNull(loan, Loan.class);

        Console console = consoleService.getById(consoleId);
        ValidationHelper.isObjectNull(console, Console.class);

        loan.getConsoles().add(console);
        loanService.update(loan);
        logger.debug("Loan updated");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "remove/console/{loanId}/{consoleId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Console Removed")
    public void removeConsole(@PathVariable Long loanId, @PathVariable Long consoleId) {
        Loan loan = loanService.getById(loanId);
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
            loanService.update(loan);
        } else {
            logger.debug("Console not found");
            throw new ObjectNotFoundException("Console not found");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "add/student/{loanId}/{studentId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Student Added")
    public void addStudent(@PathVariable Long loanId, @PathVariable Long studentId) {
        Loan loan = loanService.getById(loanId);
        ValidationHelper.isObjectNull(loan, Loan.class);

        Student student = studentService.getById(studentId);
        ValidationHelper.isObjectNull(student, Student.class);

        loan.setStudent(student);
        loanService.update(loan);
        logger.debug("Loan updated");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "remove/student/{gameId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Student Removed")
    public void removeStudent(Long loanId) {
        Loan loan = loanService.getById(loanId);
        ValidationHelper.isObjectNull(loan, Loan.class);

        boolean isRemoved = false;

        if (loan.getStudent() != null) {
            loan.setStudent(null);
            isRemoved = true;
        }

        if (isRemoved) {
            loanService.update(loan);
        } else {
            logger.debug("Loaned by not found");
            throw new ObjectNotFoundException("Loaned by not found");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenericService<Loan> getService() {
        return loanService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListAdapter<Loan> getList() {
        return loanList;
    }
}

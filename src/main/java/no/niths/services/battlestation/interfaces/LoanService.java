package no.niths.services.battlestation.interfaces;

import no.niths.domain.battlestation.Loan;
import no.niths.services.interfaces.GenericService;

import java.util.GregorianCalendar;
import java.util.List;

public interface LoanService extends GenericService<Loan> {

    void addConsole(Long loanId, Long consoleId);

    void removeConsole(Long loanId, Long consoleId);

    void addStudent(Long loanId, Long studentId);

    void removeStudent(Long loanId);

    List<Loan> getLoansBetweenDates(GregorianCalendar startTime, GregorianCalendar endTime);
}

package no.niths.infrastructure.battlestation.interfaces;

import no.niths.domain.battlestation.Loan;
import no.niths.infrastructure.interfaces.GenericRepository;

import java.util.GregorianCalendar;
import java.util.List;

public interface LoanRepository extends GenericRepository<Loan> {

    List<Loan> getLoansBetweenDates(GregorianCalendar startTime, GregorianCalendar endTime);
}

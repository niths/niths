package no.niths.infrastructure.interfaces;

import no.niths.domain.battlestation.Loan;

import java.util.GregorianCalendar;
import java.util.List;

public interface LoanRepository extends GenericRepository<Loan> {

    List<Loan> getLoansBetweenDates(GregorianCalendar startTime, GregorianCalendar endTime);
}

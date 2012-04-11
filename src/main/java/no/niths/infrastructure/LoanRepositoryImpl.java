package no.niths.infrastructure;

import no.niths.domain.Loan;
import no.niths.infrastructure.interfaces.LoanRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LoanRepositoryImpl extends AbstractGenericRepositoryImpl<Loan> implements LoanRepository {

    public LoanRepositoryImpl() {
        super(Loan.class, new Loan());
    }
}

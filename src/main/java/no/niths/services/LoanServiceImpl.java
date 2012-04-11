package no.niths.services;

import no.niths.domain.Loan;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.interfaces.LoanRepository;
import no.niths.services.interfaces.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoanServiceImpl extends AbstractGenericService<Loan> implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public Loan getById(long id) {
        Loan loan = loanRepository.getById(id);

        if (loan != null) {
            loan.getGames().size();
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
}

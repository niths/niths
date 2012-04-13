package no.niths.services.battlestation;

import no.niths.domain.battlestation.Console;
import no.niths.infrastructure.interfaces.ConsoleRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.battlestation.interfaces.ConsoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConsoleServiceImpl extends AbstractGenericService<Console> implements ConsoleService {

    @Autowired
    private ConsoleRepository consoleRepository;

    public Console getById(long id) {
        Console console = consoleRepository.getById(id);

        if (console != null) {
            console.getGames().size();

            if (console.getLoan() != null) {
                console.getLoan().getLoanDate();
            }
        }
        return console;
    }

    @Override
    public GenericRepository<Console> getRepository() {
        return consoleRepository;
    }
}

package no.niths.infrastructure.battlestation;

import no.niths.domain.battlestation.Console;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.battlestation.interfaces.ConsoleRepository;

import org.springframework.stereotype.Repository;

@Repository
public class ConsoleRepositoryImpl extends AbstractGenericRepositoryImpl<Console> implements ConsoleRepository {

    public ConsoleRepositoryImpl() {
        super(Console.class, new Console());
    }
}

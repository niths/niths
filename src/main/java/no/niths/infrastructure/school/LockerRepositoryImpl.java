package no.niths.infrastructure.school;

import no.niths.domain.school.Locker;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.school.interfaces.LockerRepository;

import org.springframework.stereotype.Repository;
/**
 * Repository class for Locker
 *
 * <p>
 * Inherits the basic CRUD actions
 * </p>
 */
@Repository
public class LockerRepositoryImpl
        extends AbstractGenericRepositoryImpl<Locker>
        implements LockerRepository {

    public LockerRepositoryImpl() {
        super(Locker.class, new Locker());
    }
}
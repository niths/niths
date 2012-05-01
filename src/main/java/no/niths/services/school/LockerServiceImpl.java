package no.niths.services.school;

import no.niths.domain.school.Locker;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.school.interfaces.LockerRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.school.interfaces.LockerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Service Class for Locker
 *
 * <p>
 * Inherits the basic CRUD actions
 * </p>
 */
@Service
public class LockerServiceImpl extends AbstractGenericService<Locker>
        implements LockerService {

    @Autowired
    private LockerRepository repo;

    @Override
    public GenericRepository<Locker> getRepository() {
        return repo;
    }
}
package no.niths.services.location;

import java.util.List;

import no.niths.domain.location.Location;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.location.interfaces.LocationRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.location.interfaces.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Service Class for Location
 *
 * <p>
 * Inherits the basic CRUD actions
 * </p>
 */
@Service
public class LocationServiceImpl extends AbstractGenericService<Location>
        implements LocationService {

    @Autowired
    private LocationRepository repo;

    @Override
    public List<Location> getPredefinedLocations() {
        return null;
    }

    @Override
    public GenericRepository<Location> getRepository() {
        return repo;
    }
}

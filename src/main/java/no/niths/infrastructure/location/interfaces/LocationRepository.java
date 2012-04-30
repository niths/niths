package no.niths.infrastructure.location.interfaces;

import java.util.List;

import no.niths.domain.location.Location;
import no.niths.infrastructure.interfaces.GenericRepository;
/**
 * Repository class for Location
 *
 * <p>
 * Inherits the basic CRUD actions
 * </p>
 */
public interface LocationRepository extends GenericRepository<Location>{

    List<Location> getPredefinedLocations();
}

package no.niths.infrastructure.location;

import java.util.List;

import no.niths.domain.location.Location;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.location.interfaces.LocationRepository;

import org.springframework.stereotype.Repository;
/**
 * Repository class for Location
 *
 * <p>
 * Inherits the basic CRUD actions
 * </p>
 */
@Repository
public class LocationRepositoryImpl extends
		AbstractGenericRepositoryImpl<Location> implements LocationRepository {

    @Override
    public Long create(Location location) {
        return super.create(location);
    }

    public List<Location> getPredefinedLocations() {
        return null;
        
    }

	public LocationRepositoryImpl() {
		super(Location.class, new Location());
	}
}

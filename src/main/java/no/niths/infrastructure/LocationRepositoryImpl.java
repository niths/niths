package no.niths.infrastructure;

import org.springframework.stereotype.Repository;

import no.niths.domain.location.Location;
import no.niths.infrastructure.interfaces.LocationRepository;

@Repository
public class LocationRepositoryImpl extends
		AbstractGenericRepositoryImpl<Location> implements LocationRepository {

	public LocationRepositoryImpl() {
		super(Location.class, new Location());
	}
}

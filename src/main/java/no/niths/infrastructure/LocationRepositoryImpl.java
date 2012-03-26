package no.niths.infrastructure;

import no.niths.domain.location.Location;
import no.niths.infrastructure.interfaces.LocationRepository;

import org.springframework.stereotype.Repository;

@Repository
public class LocationRepositoryImpl extends
		AbstractGenericRepositoryImpl<Location> implements LocationRepository {

	public LocationRepositoryImpl() {
		super(Location.class);
	}

	@Override
	public void hibernateDelete(long id) {
		Location location = new Location();
		location.setId(id);
		getSession().getCurrentSession().delete(location);

	}

}

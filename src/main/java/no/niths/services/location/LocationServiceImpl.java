package no.niths.services.location;

import no.niths.domain.location.Location;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.interfaces.LocationRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.location.interfaces.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl extends AbstractGenericService<Location>
		implements LocationService {

	@Autowired
	private LocationRepository repo;

	@Override
	public GenericRepository<Location> getRepository() {
		return repo;
	}

}

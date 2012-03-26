package no.niths.services;

import no.niths.domain.location.Location;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.interfaces.LocationRepository;
import no.niths.services.interfaces.LocationService;

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

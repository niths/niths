package no.niths.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import no.niths.domain.location.Location;
import no.niths.infrastructure.interfaces.LocationRepository;
import no.niths.services.interfaces.LocationService;
@Service
@Transactional
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository repo;
	
	@Override
	public Long create(Location domain) {
		return repo.create(domain);
	}

	@Override
	public List<Location> getAll(Location domain) {
			
		List<Location> locs = repo.getAll(domain);
		
//		for(Location l:locs){
//			l.setEventLocations(null);
//		}
		
		return locs;
	}

	@Override
	public Location getById(long id) {
		Location temp = repo.getById(id);
//		if(temp != null){
//			temp.getEventLocations().size();
//		}
		
		return temp;
	}

	@Override
	public void update(Location domain) {
		repo.update(domain);
	}

	@Override
	public boolean delete(long id) {
		return repo.delete(id);
	}

	@Override
	public void hibernateDelete(long id) {
		repo.hibernateDelete(id);

	}

}

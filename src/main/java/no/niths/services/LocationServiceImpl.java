package no.niths.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import no.niths.domain.location.Location;
import no.niths.infrastructure.interfaces.LocationRepository;
import no.niths.services.interfaces.LocationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class LocationServiceImpl implements LocationService {

	private Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);

	private CustomBeanUtilsBean beanCopy = new CustomBeanUtilsBean();

	@Autowired
	private LocationRepository repo;
	
	@Override
	public Long create(Location domain) {
		return repo.create(domain);
	}

	@Override
	public List<Location> getAll(Location domain) {
			
		List<Location> locs = repo.getAll(domain);
		return locs;
	}

	@Override
	public Location getById(long id) {
		Location temp = repo.getById(id);
		return temp;
	}

	@Override
	public void update(Location location) {
		Location locationToUpdate = repo.getById(location.getId());
		try {
			beanCopy.copyProperties(locationToUpdate, location);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error",e);
			e.printStackTrace();
		}
		repo.update(locationToUpdate);
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

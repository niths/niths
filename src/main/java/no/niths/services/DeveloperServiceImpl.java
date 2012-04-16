package no.niths.services;

import java.util.List;

import no.niths.domain.Developer;
import no.niths.infrastructure.interfaces.DeveloperRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.interfaces.DeveloperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeveloperServiceImpl extends AbstractGenericService<Developer> implements DeveloperService{

    @Autowired
    private DeveloperRepository repo;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Deprecated
	public Developer getDeveloperByDeveloperToken(String token, boolean isEnabled) {
		return repo.getByDeveloperToken(token, isEnabled);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Developer getDeveloperByDeveloperKey(String key) {
		Developer dev = repo.getByDeveloperKey(key);
		if(dev != null){
			dev.getApps().size();
		}
		return dev;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Developer> getAllWithApps(Developer dev) {
		List<Developer> all = repo.getAll(dev);
		for (Developer d: all){
			d.getApps().size();
		}
		return all;
	}

	@Override
	public GenericRepository<Developer> getRepository() {
		return repo;
	}
}
package no.niths.services.developing;

import java.util.List;

import no.niths.domain.developing.Developer;
import no.niths.infrastructure.developing.interfaces.DeveloperRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.developing.interfaces.DeveloperService;

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
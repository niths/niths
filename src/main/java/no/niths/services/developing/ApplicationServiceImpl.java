package no.niths.services.developing;

import no.niths.domain.developing.Application;
import no.niths.infrastructure.developing.interfaces.ApplicationRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.developing.interfaces.ApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl extends AbstractGenericService<Application>
		implements ApplicationService {

	@Autowired
	private ApplicationRepository repo;

	@Override
	@Deprecated
	public Application getByApplicationToken(String token) {
		return repo.getByApplicationToken(token);
	}

	/**
	 * Returns the application matching the key The application must be enabled
	 * to be returned
	 * 
	 * @param key
	 *            the application key as a string
	 * @param enabled
	 *            if the app needs to be enabled or not
	 * @return the application or null if no matching key
	 */
	@Override
	public Application getByApplicationKey(String key, boolean enabled) {
		return repo.getByApplicationKey(key, enabled);
	}

	@Override
	public GenericRepository<Application> getRepository() {
		return repo;
	}
}
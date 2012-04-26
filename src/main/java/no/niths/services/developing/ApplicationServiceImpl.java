package no.niths.services.developing;

import java.util.List;

import no.niths.domain.developing.Application;
import no.niths.domain.developing.Developer;
import no.niths.infrastructure.developing.interfaces.ApplicationRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.developing.interfaces.ApplicationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for Application
 *
 */
@Service
public class ApplicationServiceImpl extends AbstractGenericService<Application>
		implements ApplicationService {
	
	private static final Logger logger = LoggerFactory
			.getLogger(ApplicationServiceImpl.class);
	
	@Autowired
	private ApplicationRepository repo;

	@Override
	@Deprecated
	public Application getByApplicationToken(String token) {
		return repo.getByApplicationToken(token);
	}
	
	/**
	 * Returns a list applications ordered
	 * by the number of requests @See {@link Application}
	 * 
	 * @param maxResults number of results
	 * @return list with maxResults applications
	 */
	@Override
	public List<Application> getTopApps(int maxResults){
		return repo.getTopApps(maxResults);
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

	/**
	 * Enables an application
	 * 
	 * @param applicationId id of the application to enable
	 * @throws ObjectNotFoundException if the app does not exist
	 */
	@Override
	public void enableApplication(Long applicationId) {
		Application applications = validate(repo.getById(applicationId),
				Application.class);
		if ((applications.getEnabled() != null) || (!applications.getEnabled())) {
			applications.setEnabled(true);
			logger.debug("Application " + applications.getTitle() + " is enabled");
		}
		
	}

	/**
	 * Disables an application
	 * 
	 * @param applicationId id of the application to enable
	 * @throws ObjectNotFoundException if the app does not exist
	 */
	@Override
	public void disableApplication(Long applicationId) {
		Application applicatipns = validate(repo.getById(applicationId),
				Developer.class);
		if (applicatipns.getEnabled() != null && applicatipns.getEnabled()) {
			applicatipns.setEnabled(false);
			logger.debug("Application " + applicatipns.getTitle() + " is disabled");
		}
		
	}
}
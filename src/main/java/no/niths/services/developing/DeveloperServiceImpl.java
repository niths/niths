package no.niths.services.developing;

import java.util.List;

import no.niths.application.rest.helper.Status;
import no.niths.common.helpers.MessageProvider;
import no.niths.common.helpers.ValidationHelper;
import no.niths.domain.developing.Application;
import no.niths.domain.developing.Developer;
import no.niths.domain.school.Committee;
import no.niths.infrastructure.developing.interfaces.ApplicationRepository;
import no.niths.infrastructure.developing.interfaces.DeveloperRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.developing.interfaces.DeveloperService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Service class for Developer
 *
 */
@Service
public class DeveloperServiceImpl extends AbstractGenericService<Developer>
		implements DeveloperService {

	@Autowired
	private DeveloperRepository repo;

	@Autowired
	private ApplicationRepository applicationRepo;

	private Logger logger = LoggerFactory.getLogger(DeveloperServiceImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Deprecated
	public Developer getDeveloperByDeveloperToken(String token,
			boolean isEnabled) {
		return repo.getByDeveloperToken(token, isEnabled);
	}

	/**
	 * Returns the developer with the matching developer token
	 * 
	 * @param token string with the developer key
	 * @return the developer or null if no developer were found
	 */
	@Override
	public Developer getDeveloperByDeveloperKey(String key) {
		Developer dev = repo.getByDeveloperKey(key);
		if (dev != null) {
			dev.getApps().size();
		}
		return dev;
	}

	/**
	 * Returns all developers with and their applications
	 * @param dev a developer object with attributes to limit the result set,
	 * 			if null, all developers will be returned
	 * @return list of all developers
	 */
	@Override
	public List<Developer> getAllWithApps(Developer dev) {
		List<Developer> all = repo.getAll(dev);
		for (Developer d : all) {
			d.getApps().size();
		}
		return all;
	}

	@Override
	public GenericRepository<Developer> getRepository() {
		return repo;
	}

	/**
	 * Enables the developer with matching id
	 * 
	 * @param developerId id of the developer to enable
	 * @throws ObjectNotFoundException when dev does not exist
	 */
	@Override
	public void enableDeveloper(Long developerId) {
		Developer developer = validate(repo.getById(developerId),
				Developer.class);
		developer.setEnabled(true);
		logger.debug("Developer " + developer.getName() + " is enabled");
	}

	/**
	 * Disables the developer with matching id
	 * 
	 * @param developerId id of the developer to disable
	 * @throws ObjectNotFoundException when dev does not exist
	 */
	@Override
	public void disableDeveloper(Long developerId) {
		Developer developer = validate(repo.getById(developerId),
				Developer.class);
		developer.setEnabled(false);
		logger.debug("Developer " + developer.getName() + " is disabled");

	}
	
	/**
	 * Resets the developer key.
	 * Deletes the old one and generates a new
	 * 
	 * @param developerId id of the developer
	 * @param generatedDeveloperKey the new key
	 * @throws ObjectNotFoundException when dev does not exist
	 */
	@Override
	public void resetDeveloperKey(Long developerId, String generatedDeveloperKey) {
		Developer developer = validate(repo.getById(developerId),
				Developer.class);
		logger.debug("OLD-dev key :" + developer.getDeveloperKey()
				+ "\n new developer key :" + generatedDeveloperKey);
		developer.setDeveloperKey(generatedDeveloperKey);
	}

	/**
	 * Adds an application to a developer
	 * 
	 * @param developerId id of the developer
	 * @param applicationId id of the application
	 * @throws ObjectNotFoundException when app or dev does not exist
	 */
	@Override
	public void addApplication(Long developerId, Long applicationId) {
		Developer developer = validate(repo.getById(developerId),
				Developer.class);
		checkIfObjectIsInCollection(developer.getApps(), applicationId,
				Application.class);

		Application app = applicationRepo.getById(applicationId);
		ValidationHelper.isObjectNull(app, Application.class);

		developer.getApps().add(app);
		logger.debug(MessageProvider.buildStatusMsg(Committee.class,
				Status.UPDATED));
	}

	/**
	 * Removes an application from a developer
	 * 
	 * @param developerId id of the developer
	 * @param applicationId id of the application
	 * @throws ObjectNotFoundException when app or dev does not exist
	 */
	@Override
	public void removeApplicaiton(Long developerId, Long applicationId) {
		Developer developer = validate(repo.getById(developerId),
				Developer.class);
		checkIfIsRemoved(
				developer.getApps().remove(new Application(applicationId)),
				Application.class);
	}
}
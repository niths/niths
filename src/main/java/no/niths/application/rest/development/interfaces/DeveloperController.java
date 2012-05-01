package no.niths.application.rest.development.interfaces;

import org.springframework.web.bind.annotation.PathVariable;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.development.Developer;
/**
 * Controller for handling developers and their applications
 *
 */
public interface DeveloperController extends GenericRESTController<Developer> {
	
	/**
	 * Adds a app to a developer
	 * @param devId
	 * @param AppId
	 */
	void addApplication(Long devId, Long applicationId);
	
	/**
	 * Removes a app from a developer
	 * @param devId
	 * @param AppId
	 */
	void removeApplication(Long devId, Long applicationId);

	/**
	 * Enables a developer
	 * <p>
	 * Developer must be enabled to do request
	 * <p>
	 * @param developerId id of the developer
	 * @throws ObjectNotFoundException if no developer is found
	 */
	void enableDeveloper(Long developerId);

	/**
	 * Reset developer key
	 * @param devId id of the developer
	 * @throws ObjectNotFoundException id no dev is found
	 */
	void resetDeveloperKey(Long devloperId);
	
	
	/**
	 * Disables a developer, so he can't make request
	 * @param developerId
	 */
	void disableDeveloper(@PathVariable Long developerId);

}

package no.niths.application.rest.interfaces;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.domain.Developer;
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
	void addApp(Long devId, Long AppId);
	
	/**
	 * Removes a app from a developer
	 * @param devId
	 * @param AppId
	 */
	void removeApp(Long devId, Long AppId);

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
	void resetDeveloperKey(Long devId);

}

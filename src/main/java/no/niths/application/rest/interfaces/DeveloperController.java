package no.niths.application.rest.interfaces;

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

}

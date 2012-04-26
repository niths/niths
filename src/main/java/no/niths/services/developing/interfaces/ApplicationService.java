package no.niths.services.developing.interfaces;

import java.util.List;

import no.niths.domain.developing.Application;
import no.niths.services.interfaces.GenericService;
/**
 * Service class for Application
 *
 */
public interface ApplicationService extends GenericService<Application> {

	@Deprecated
	Application getByApplicationToken(String token);

	/**
	 * Returns the application matching the key
	 * The application must be enabled to be returned
	 * 
	 * @param key the application key as a string
	 * @param enabled if the app needs to be enabled or not
	 * @return the application or null if no matching key or app is not enabled
	 */
	Application getByApplicationKey(String key, boolean enabled);

	/**
	 * Enables an application
	 * 
	 * @param applicationId id of the application to enable
	 * @throws ObjectNotFoundException if the app does not exist
	 */
	void enableApplication(Long applicationId);

	/**
	 * Disables an application
	 * 
	 * @param applicationId id of the application to enable
	 * @throws ObjectNotFoundException if the app does not exist
	 */
	void disableApplication(Long applicationId);

	/**
	 * Returns a list applications ordered
	 * by the number of requests @See {@link Application}
	 * 
	 * @param maxResults number of results
	 * @return list with maxResults applications
	 */
	List<Application> getTopApps(int maxResults);
}

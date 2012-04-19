package no.niths.services.developing.interfaces;

import no.niths.domain.developing.Application;
import no.niths.services.interfaces.GenericService;

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
	 * 
	 * @param applicationId
	 */
	void enableApplication(Long applicationId);

	/**
	 * 
	 * @param applicationId
	 */
	void disableApplication(Long applicationId);
}

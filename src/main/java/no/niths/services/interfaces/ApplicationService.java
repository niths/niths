package no.niths.services.interfaces;

import no.niths.domain.Application;

public interface ApplicationService extends GenericService<Application> {

	@Deprecated
	Application getByApplicationToken(String token);

	/**
	 * Returns the application matching the key
	 * The application must be enabled to be returned
	 * 
	 * @param key the application key as a string
	 * @return the application or null if no matching key or app is not enabled
	 */
	Application getByApplicationKey(String key);
}

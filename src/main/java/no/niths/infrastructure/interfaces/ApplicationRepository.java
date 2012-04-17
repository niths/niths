package no.niths.infrastructure.interfaces;

import no.niths.domain.developing.Application;

public interface ApplicationRepository extends GenericRepository<Application> {

	@Deprecated
	Application getByApplicationToken(String token);

	/**
	 * Returns the application matching the key
	 * The application must be enabled to be returned
	 * 
	 * @param key the application key as a string
	 * @return the application or null if no matching key or app is not enabled
	 */
	Application getByApplicationKey(String key, boolean enabled);
}
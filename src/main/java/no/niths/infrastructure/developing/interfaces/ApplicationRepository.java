package no.niths.infrastructure.developing.interfaces;

import java.util.List;

import no.niths.domain.developing.Application;
import no.niths.infrastructure.interfaces.GenericRepository;

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

	/**
	 * Returns a list applications ordered
	 * by the number of requests @See {@link Application}
	 * 
	 * @param maxResults number of results
	 * @return list with maxResults applications
	 */
	List<Application> getTopApps(int maxResults);
}
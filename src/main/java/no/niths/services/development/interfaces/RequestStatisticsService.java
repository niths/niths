package no.niths.services.development.interfaces;

import no.niths.domain.development.Application;

/**
 * Service that handles request statistics
 * <p>
 * This class showcases the possibility to track request info,
 * for instance: Which application is the most popular, has the
 * most users, which resource is most asked for etc.
 * </p>
 * <p>
 * As of now, only requests where application token + 
 * developer token is tracked.
 * </p>
 *
 */
public interface RequestStatisticsService {
	
	/**
	 * Simple example method for tracking app statistics
	 * 
	 * @param app the application that holds the request
	 */
	public void registerRequest(Application app);

}

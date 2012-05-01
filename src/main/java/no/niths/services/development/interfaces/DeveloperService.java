package no.niths.services.development.interfaces;

import java.util.List;

import no.niths.domain.development.Developer;
import no.niths.services.interfaces.GenericService;
/**
 * Service class for Developer
 *
 */
public interface DeveloperService extends GenericService<Developer> {
	
	/**
	 * Returns the developer with the matching developer token
	 * 
	 * @param token string with the developer token
	 * @param isEnabled set to tru if you want dev that is enabled
	 * @return the developer or null if no developer were found
	 */
	@Deprecated
	Developer getDeveloperByDeveloperToken(String token, boolean isEnabled);
	
	/**
	 * Returns all developers with and their applications
	 * @param dev a developer object with attributes to limit the result set,
	 * 			if null, all developers will be returned
	 * @return list of all developers
	 */
	List<Developer> getAllWithApps(Developer dev);

	/**
	 * Returns the developer with the matching developer token
	 * 
	 * @param token string with the developer key
	 * @return the developer or null if no developer were found
	 */
	Developer getDeveloperByDeveloperKey(String key);

	/**
	 * Enables the developer with matching id
	 * 
	 * @param developerId id of the developer to enable
	 * @throws ObjectNotFoundException when dev does not exist
	 */
	void enableDeveloper(Long developerId);

	/**
	 * Disables the developer with matching id
	 * 
	 * @param developerId id of the developer to disable
	 * @throws ObjectNotFoundException when dev does not exist
	 */
	void disableDeveloper(Long developerId);

	/**
	 * Resets the developer key.
	 * Deletes the old one and generates a new
	 * 
	 * @param developerId id of the developer
	 * @param generatedDeveloperKey the new key
	 * @throws ObjectNotFoundException when dev does not exist
	 */
	void resetDeveloperKey(Long developerId, String generatedDeveloperKey);

	/**
	 * Adds an application to a developer
	 * 
	 * @param developerId id of the developer
	 * @param applicationId id of the application
	 * @throws ObjectNotFoundException when app or dev does not exist
	 */
	void addApplication(Long developerId, Long applicationId);

	/**
	 * Removes an application from a developer
	 * 
	 * @param developerId id of the developer
	 * @param applicationId id of the application
	 * @throws ObjectNotFoundException when app or dev does not exist
	 */
	void removeApplicaiton(Long developerId, Long applicationId);

	
}

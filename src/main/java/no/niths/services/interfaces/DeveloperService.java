package no.niths.services.interfaces;

import no.niths.domain.Developer;

public interface DeveloperService extends GenericService<Developer> {
	
	/**
	 * Returns the developer with the matching developer token
	 * 
	 * @param token string with the developer token
	 * @param isEnabled set to tru if you want dev that is enabled
	 * @return the developer or null if no developer were found
	 */
	Developer getDeveloperByDeveloperToken(String token, boolean isEnabled);

}

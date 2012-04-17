package no.niths.infrastructure.developing.interfaces;

import no.niths.domain.developing.Developer;
import no.niths.infrastructure.interfaces.GenericRepository;

public interface DeveloperRepository extends GenericRepository<Developer> {
	@Deprecated
	Developer getByDeveloperToken(String token, boolean isEnabled);

	/**
	 * Returns the developer with matching key, 
	 * or null if no developer is found
	 * 
	 * @param key the developer key
	 * @return a developer or null
	 */
	Developer getByDeveloperKey(String key);
}
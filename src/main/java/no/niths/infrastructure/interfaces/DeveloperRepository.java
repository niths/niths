package no.niths.infrastructure.interfaces;

import no.niths.domain.Developer;

public interface DeveloperRepository extends GenericRepository<Developer> {
	Developer getByDeveloperToken(String token, boolean isEnabled);
}
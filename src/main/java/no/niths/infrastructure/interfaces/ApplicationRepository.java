package no.niths.infrastructure.interfaces;

import no.niths.domain.Application;

public interface ApplicationRepository extends GenericRepository<Application> {

	Application getByApplicationToken(String token);
}
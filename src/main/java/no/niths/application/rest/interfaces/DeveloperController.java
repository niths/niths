package no.niths.application.rest.interfaces;

import no.niths.domain.Developer;
/**
 * Controller for handling developers and their applications
 *
 */
public interface DeveloperController extends GenericRESTController<Developer> {
	/**
	 * Method used for developers wanting access to the API
	 * @param email
	 * @return
	 */
	String requestAccess(String email, String developerName);
}

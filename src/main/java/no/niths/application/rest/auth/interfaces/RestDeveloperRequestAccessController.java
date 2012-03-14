package no.niths.application.rest.auth.interfaces;


/**
 * Rest controller for developers that want access to the API
 * 
 */
public interface RestDeveloperRequestAccessController {

	/**
	 * Takes a developer email and sends an email with further instructions
	 * on to be enabled as a developer
	 * 
	 * @param email Developer email
	 * @return String with confirmation
	 */
	public String requestAccess(String email);
	
}

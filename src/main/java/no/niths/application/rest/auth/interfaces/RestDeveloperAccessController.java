package no.niths.application.rest.auth.interfaces;

import org.springframework.web.servlet.ModelAndView;

import no.niths.domain.Application;
import no.niths.domain.Developer;
import no.niths.security.ApplicationToken;
import no.niths.security.DeveloperToken;

/**
 * Handles developers wanting access to the API
 * 
 */
public interface RestDeveloperAccessController {
	
	/**
	 * Register a developer and generates a developer token that the
	 * developer uses in future requests
	 * 
	 * <pre>
	 * {@code
	 * How to use:
	 * POST: niths/register/
	 * 
	 * Header:
	 * Content-type: application/xml
	 * Accept: application/xml || application/json
	 * 
	 * Body:
	 * <developer>
	 * <email>youremail@mail.com</email>
	 * <name>Your developer name</name>
	 * </developer>
	 * }
	 * </pre>
	 * 
	 * @param developer the developer to persist
	 * @return DeveloperToken the token and a confirmation message 
	 */
	DeveloperToken requestAccess(Developer domain);
	
	/**
	 * Enables already registrated developers.
	 * 
	 * How to use:
	 * Paste the url to the server + /niths/register/enable/<your_token>
	 * into your favourite browser
	 * 
	 * @param developerToken the token returned from requestAccess(Developer)
	 * @return a page with confirmation or error message
	 */
	ModelAndView enableDeveloper(String developerToken);

	/**
	 * Registers an application
	 * 
	 * Developer must have been authorized for a successful request
	 * 
	 * @param app the application to add
	 * @return an application token to use in furture requests
	 * 
	 */
	ApplicationToken addApplicationToDeveloper(Application app, String developerToken);
	
}

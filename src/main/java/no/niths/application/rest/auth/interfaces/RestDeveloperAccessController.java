package no.niths.application.rest.auth.interfaces;

import no.niths.domain.developing.Application;
import no.niths.domain.developing.Developer;
import no.niths.security.ApplicationToken;
import no.niths.security.DeveloperToken;

import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * Handles developers wanting access to the API
 * <p>
 * Developers must register with the API to be able to
 * develop applications that can access the restricted
 * resources in the API
 * </p>
 * <p>
 * Developers register, then confirms their identity and
 * then they can register and enable applications
 * </p>
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

	/**
	 * Enables an application
	 * 
	 * @param applicationKey
	 * @return a view with confirmation
	 */
	ModelAndView enableApplication(String applicationKey);
	
}

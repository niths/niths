package no.niths.application.rest.auth.interfaces;

import org.springframework.web.servlet.ModelAndView;

import no.niths.domain.Developer;
import no.niths.security.DeveloperToken;

/**
 * Handles developers wanting access to the API
 * 
 */
public interface RestDeveloperAccessController {
	
	/**
	 * Method used for developers wanting access to the API
	 * @param developer
	 */
	DeveloperToken requestAccess(Developer domain);
	
	ModelAndView enableDeveloper(String developerToken);
	
}

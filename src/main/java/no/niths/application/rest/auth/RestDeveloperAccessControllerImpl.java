package no.niths.application.rest.auth;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;

import no.niths.application.rest.RESTConstants;
import no.niths.application.rest.auth.interfaces.RestDeveloperAccessController;
import no.niths.application.rest.exception.BadRequestException;
import no.niths.application.rest.exception.DuplicateEntryCollectionException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.exception.UnvalidEmailException;
import no.niths.domain.developing.Application;
import no.niths.domain.developing.Developer;
import no.niths.security.ApplicationToken;
import no.niths.security.DeveloperToken;
import no.niths.services.auth.interfaces.AuthenticationService;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Register developers wanting access to the API
 * 
 */
@Controller
@RequestMapping("register")
public class RestDeveloperAccessControllerImpl implements
		RestDeveloperAccessController {

	Logger logger = org.slf4j.LoggerFactory
			.getLogger(RestDeveloperAccessControllerImpl.class);

	@Autowired
	private AuthenticationService service;

	private final static String VIEW_NAME = "developerConfirmation";

	/**
	 * Register a developer and generates a developer token that the developer
	 * uses in future requests
	 * <p>
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
	 * @param developer
	 *            the developer to persist
	 * @return DeveloperToken 
	 * 				an object containing the developer key and a confirmation message
	 */
	@Override
	@RequestMapping(method = RequestMethod.POST, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public DeveloperToken requestAccess(@RequestBody Developer developer) {
		logger.debug("A developer requests access! Email: "
				+ developer.getEmail());
		if(developer.getApps() != null){
			throw new BadRequestException("You must register as a developer before registering apps");
		}
		DeveloperToken devToken = service.registerDeveloper(developer);

		logger.debug("Request success, sending email");

		return devToken;
	}

	/**
	 * Enables already registred developers. Returns a new developer token to
	 * use in all future requests
	 * <p>
	 * How to use: Paste the url to the server +
	 * /niths/register/enableDeveloper/<your_key> into your favourite browser
	 * <p>
	 * @param developerKey
	 *            the token returned from requestAccess(Developer)
	 * @return a page with confirmation or error message
	 */
	@Override
	@RequestMapping(value = { "/enableDeveloper/{developerKey:.+}" }, method = RequestMethod.GET)
	public ModelAndView enableDeveloper(@PathVariable String developerKey) {
		logger.debug("Developer wants to be enabled with developer-key: "
				+ developerKey);
		ModelAndView view = new ModelAndView(VIEW_NAME);
		try {
			
			Developer dev = service.enableDeveloper(developerKey);
			
			view.addObject("msg", "Your developer account is enabled!");
			
			// Returns a view with the new token
			view.addObject("token","Developer-token: " + dev.getDeveloperToken());
			view.addObject("key", "Developer-key: " + dev.getDeveloperKey());

		} catch (AuthenticationException e) {
			view.addObject("error", e.getMessage());
		}
		
		return view;
	}
	
	/**
	 * Registers an application
	 * <p>
	 * Developer must have been authorized for a successful request
	 * <p>
	 * @param app the application to add
	 * @param developerKey the developer key
	 * @return an application key to use to enable the application
	 * 
	 */
	@Override
	@RequestMapping(value = "/addApp/{developerKey}", method = RequestMethod.POST, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public ApplicationToken addApplicationToDeveloper(@RequestBody Application app,
										@PathVariable String developerKey) {
		logger.debug("Developer wants to registrate an application");
		ApplicationToken token = new ApplicationToken("Could not register app");
		
		token = service.registerApplication(app, developerKey);
		return token;
	}
	
	/**
	 * Enables an application
	 * 
	 * @param applicationKey
	 * @return a view with confirmation
	 */
	@Override
	@RequestMapping(value = { "/enableApp/{applicationKey:.+}" }, method = RequestMethod.GET)
	public ModelAndView enableApplication(@PathVariable String applicationKey) {
		logger.debug("Application wants to be enabled with application-key: "
				+ applicationKey);
		ModelAndView view = new ModelAndView(VIEW_NAME);
		try {
			Application app = service.enableApplication(applicationKey);
			view.addObject("msg", "Your app is enabled!");
			// Returns a view with the new token
			view.addObject("token", "Application-token: " + app.getApplicationToken());
			view.addObject("key", "Application-key: " + app.getApplicationKey());
			
		} catch (AuthenticationException e) {
			view.addObject("error", e.getMessage());
		}
		
		return view;
	}

	@ExceptionHandler(DuplicateEntryCollectionException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public void dataIntegrity(DuplicateEntryCollectionException e,
			HttpServletResponse res) {
		res.setHeader("Error", e.getMessage().toString());
	}
	@ExceptionHandler(ObjectNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void dataIntegrity(ObjectNotFoundException e,
			HttpServletResponse res) {
		res.setHeader("Error", e.getMessage().toString());
	}
	@ExceptionHandler(UnvalidEmailException.class)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public void unvalidEmail(UnvalidEmailException e,
			HttpServletResponse res) {
		res.setHeader("Error", e.getMessage().toString());
	}
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public void dataIntegrity(DataIntegrityViolationException e,
			HttpServletResponse res) {
		res.setHeader("Error", e.getMessage().toString());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public void constraintViolation(ConstraintViolationException cve,
			HttpServletResponse res) {
		res.setHeader("Error", cve.getMessage().toString());
	}
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public void badReq(BadRequestException cve,
			HttpServletResponse res) {
		res.setHeader("Error", cve.getMessage().toString());
	}
	@ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public void notReadable(org.springframework.http.converter.HttpMessageNotReadableException cve,
			HttpServletResponse res) {
		res.setHeader("Error", "Request body is not correct");
	}
	
	   @ExceptionHandler(javax.validation.ConstraintViolationException.class)
	    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	    public void constraintViolation2(
	            javax.validation.ConstraintViolationException cve,
	            HttpServletResponse res) {
	        logger.debug("javax.constraint");
	        String error = "";
	        Set<ConstraintViolation<?>>  constr = cve.getConstraintViolations();
	        for (Iterator<ConstraintViolation<?>> iterator = constr.iterator(); iterator.hasNext();) {
				ConstraintViolation<?> constraintViolation = (ConstraintViolation<?>) iterator.next();
				error += constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage() + " ";
			}
	        if(error.equals("")){
	        	error = cve.getMessage();
	        }
	        res.setHeader("Error", error);
	    }

}

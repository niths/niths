package no.niths.application.rest.auth;

import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.RESTConstants;
import no.niths.application.rest.auth.interfaces.RestDeveloperAccessController;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.domain.Application;
import no.niths.domain.Developer;
import no.niths.security.ApplicationToken;
import no.niths.security.DeveloperToken;
import no.niths.security.RequestAuthenticationInfo;
import no.niths.security.RequestHolderDetails;
import no.niths.services.auth.interfaces.AuthenticationService;
import no.niths.services.interfaces.MailSenderService;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
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

	@Autowired
	private MailSenderService mailService;

	private final static String VIEW_NAME = "developerConfirmation";

	/**
	 * Register a developer and generates a developer token that the developer
	 * uses in future requests
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
	 * @param developer
	 *            the developer to persist
	 * @return DeveloperToken the token and a confirmation message
	 */
	@Override
	@RequestMapping(method = RequestMethod.POST, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public DeveloperToken requestAccess(@RequestBody Developer developer) {
		logger.debug("A developer requests access! Email: "
				+ developer.getEmail());

		DeveloperToken devToken = service.registerDeveloper(developer);

		logger.debug("Request success, sending email");

		// Send confirmation to developer
		// If any errors occurred (Sitting behind a firewall? Port closed?),
		// we give the user instructions as a HTTP response
		if (!mailService.sendDeveloperEmail(developer)) {
			devToken.setMessage("Failed to send an email, but now worries! "
					+ "To enable your new developer account: DO THIS");
		}
		return devToken;
	}

	/**
	 * Enables already registrated developers. Returns a new developer token to
	 * use in all future requests
	 * 
	 * How to use: Paste the url to the server +
	 * /niths/register/enable/<your_token> into your favourite browser
	 * 
	 * @param developerToken
	 *            the token returned from requestAccess(Developer)
	 * @return a page with confirmation or error message
	 */
	@Override
	@RequestMapping(value = { "/enable/{developerToken:.+}" }, method = RequestMethod.GET)
	public ModelAndView enableDeveloper(@PathVariable String developerToken) {
		logger.debug("Developer want to be enabled with developer token: "
				+ developerToken);
		ModelAndView view = new ModelAndView(VIEW_NAME);
		try {
			Developer dev = service.enableDeveloper(developerToken);
			// Returns a view with the new token
			view.addObject("token", dev.getDeveloperToken());
			
			//TODO: send developer mail

		} catch (AuthenticationException e) {
			view.addObject("error", e.getMessage());
		}
		// Maybe send a new email with the new token?
		return view;
	}
	
	/**
	 * Registers an application
	 * 
	 * Developer must have been authorized for a successful request
	 * 
	 * @param app the application to add
	 * @return an application token to use in furture requests
	 * 
	 */
	@Override
	@RequestMapping(value = "/addApp", method = RequestMethod.POST, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public ApplicationToken addApplicationToDeveloper(@RequestBody Application app) {
		ApplicationToken token = new ApplicationToken("Could not register app");
		logger.debug("Developer wants to registrate an application");
		//First we get the information about the current request holder
		//If no developer is found, cancel the request,
		//else add application to developer
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null && auth instanceof RequestAuthenticationInfo) {
			RequestHolderDetails det = (RequestHolderDetails) auth.getPrincipal();
			logger.debug("Found authentication! Developer id: " + det.getDeveloperId());
			
			logger.debug("Adding new app to developer");
			token = service.registerApplication(app, det.getDeveloperId());
			token.setMessage("Use this in the header for futurer requests");
			
			//TODO: send developer an email
			
		}else{
			throw new ObjectNotFoundException("Are you sure you set the correct developer-token?");
		}
		return token;
	}

	@ExceptionHandler(ObjectNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void dataIntegrity(ObjectNotFoundException e,
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

}

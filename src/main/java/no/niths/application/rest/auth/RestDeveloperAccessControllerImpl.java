package no.niths.application.rest.auth;

import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.RESTConstants;
import no.niths.application.rest.auth.interfaces.RestDeveloperAccessController;
import no.niths.common.AppConstants;
import no.niths.domain.Developer;
import no.niths.security.DeveloperToken;
import no.niths.services.auth.interfaces.AuthenticationService;
import no.niths.services.interfaces.MailSenderService;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

/**
 * Register developers wanting access to the API
 *
 */
@Controller
@RequestMapping("register")
public class RestDeveloperAccessControllerImpl implements RestDeveloperAccessController{
	
	Logger logger = org.slf4j.LoggerFactory
			.getLogger(RestDeveloperAccessControllerImpl.class);
	
	@Autowired
	private AuthenticationService service;
	
	@Autowired
	private MailSenderService mailService;
	
	/**
	 * Register a developer!
	 * 
	 * Check if the email is valid, then send the developer a email confirmation
	 * 
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(method = RequestMethod.POST, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public DeveloperToken requestAccess(@RequestBody Developer developer) {
		logger.debug("A developer requests access! Email: " + developer.getEmail());
		
		DeveloperToken devToken = service.registerDeveloper(developer);

		logger.debug("Request success, sending email");
		
		//Send confirmation to developer
		//If any errors occurred (Sitting behind a firewall? Port closed?),
		//we give the user instructions as a HTTP response
		if(!mailService.sendDeveloperEmail(developer)){
			devToken.setMessage("Failed to send an email, but now worries! " +
								"To enable your new developer account: DO THIS");
		}
		return devToken;
	}
	
	@Override
	@RequestMapping(value = { "/enable/{developerToken:.+}" }, method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	public ModelAndView enableDeveloper(@PathVariable String developerToken){
		logger.debug("Developer want to be enabled with developer token: " + developerToken);
		
		ModelAndView view = new ModelAndView("developerConfirmation");
		view.addObject("token", developerToken);
		return view;
		//return new DeveloperToken("TOOOKEN", "HEI DU");
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

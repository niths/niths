package no.niths.application.rest.auth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import no.niths.application.rest.RESTConstants;
import no.niths.application.rest.auth.interfaces.RestDeveloperRequestAccessController;
import no.niths.common.AppConstants;
import no.niths.services.interfaces.MailSenderService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Google authorization controller
 * 
 */
@Controller
@RequestMapping(AppConstants.AUTH)
public class RestDeveloperRequestAccessControllerImpl implements
		RestDeveloperRequestAccessController {

	Logger logger = org.slf4j.LoggerFactory
			.getLogger(RestDeveloperRequestAccessControllerImpl.class);

	@Autowired
	private MailSenderService mailService;
	
	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@PostConstruct
	public void init() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	@Override
	@RequestMapping(value = { "/dev/{email:.+}" }, method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public String requestAccess(@PathVariable String email) {
		logger.debug("A developer requests access: " + email);
		if(validateEmail(email)){
			logger.debug("valid email");
			
			//generere token
			//opprette en developer med den token
			//sette boolean devEnabled til false (Developer har en bool enabled kanskje)
			//sende token til dev på mail
			
			//Dev mottar denne på mail
			//sende token tilbake til APIet
			//verifisere tokenen
			//sette dev til enabled
			//Sende mail til dev med du er enabled
			
			
			mailService.composeAndSend(email);
			return "Valid email provided, check your inbox!";
		}
		logger.debug("not a valid email");
		return "Not a valid email";
	}

	private boolean validateEmail(String email) {
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

}

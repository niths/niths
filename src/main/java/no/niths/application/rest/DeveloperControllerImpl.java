package no.niths.application.rest;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import no.niths.application.rest.interfaces.DeveloperController;
import no.niths.application.rest.lists.DeveloperList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.AppConstants;
import no.niths.common.SecurityConstants;
import no.niths.domain.Developer;
import no.niths.services.interfaces.DeveloperService;
import no.niths.services.interfaces.GenericService;
import no.niths.services.interfaces.MailSenderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * Controller for handling developers and their applications
 *
 */
@Controller
@RequestMapping(AppConstants.DEVELOPERS)
public class DeveloperControllerImpl extends AbstractRESTControllerImpl<Developer> implements DeveloperController{

	private static final Logger logger = LoggerFactory
			.getLogger(DeveloperControllerImpl.class);

	@Autowired
	private DeveloperService service;

	private DeveloperList developerList = new DeveloperList();
	
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
	@RequestMapping(value = { "/gimme/{email:.+}" }, method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
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
	
	@Override
	public ArrayList<Developer> getAll(Developer domain) {
		developerList = (DeveloperList) super.getAll(domain);
		for (Developer d : developerList){
			d.setApps(null);
		}
		return developerList;
	}
	
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void create(@RequestBody Developer domain) {
		// TODO Auto-generated method stub
		super.create(domain);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void delete(@PathVariable Long id) {
		// TODO Auto-generated method stub
		super.delete(id);
	}
	
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void hibernateDelete(@PathVariable long id) {
		// TODO Auto-generated method stub
		super.hibernateDelete(id);
	}
	
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void update(@RequestBody Developer domain) {
		// TODO Auto-generated method stub
		super.update(domain);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GenericService<Developer> getService() {
		return service;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListAdapter<Developer> getList() {
		return developerList;
	}


}

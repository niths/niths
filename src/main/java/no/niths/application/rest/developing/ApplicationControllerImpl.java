package no.niths.application.rest.developing;

import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.developing.interfaces.ApplicationController;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.lists.ApplicationList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.AppNames;
import no.niths.common.SecurityConstants;
import no.niths.domain.developing.Application;
import no.niths.services.developing.interfaces.ApplicationService;
import no.niths.services.interfaces.GenericService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller for handling applications
 * 
 */
@Controller
@RequestMapping(AppNames.APPLICATIONS)
public class ApplicationControllerImpl extends
		AbstractRESTControllerImpl<Application> implements
		ApplicationController {

	@Autowired
	private ApplicationService service;

	private ApplicationList applicationList = new ApplicationList();

	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void create(
	        @RequestBody Application domain,
	        HttpServletResponse res) {
		super.create(domain, res);
	}

	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void delete(@PathVariable long id) {
		super.delete(id);
	}

	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void update(@RequestBody Application domain) {
		super.update(domain);
	}

	/**
	 * Enables an application
	 * <p>
	 * Applications must be enabled to do request
	 * <p>
	 * 
	 * @param applicationId
	 *            id of the application
	 * @throws ObjectNotFoundException
	 *             if no application is found
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	@RequestMapping(value = { "{applicationId}/enable" }, method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Application enabled")
	public void enableApplication(@PathVariable Long applicationId) {
		service.enableApplication(applicationId);
	}

	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	@RequestMapping(value = { "{applicationId}/disable" }, method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Application disable")
	public void disableApplication(@PathVariable Long applicationId){
		service.disableApplication(applicationId);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GenericService<Application> getService() {
		return service;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListAdapter<Application> getList() {
		return applicationList;
	}

}

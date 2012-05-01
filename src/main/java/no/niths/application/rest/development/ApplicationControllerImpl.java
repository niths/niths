package no.niths.application.rest.development;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.RESTConstants;
import no.niths.application.rest.development.interfaces.ApplicationController;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.development.ApplicationList;
import no.niths.common.constants.DomainConstantNames;
import no.niths.common.constants.SecurityConstants;
import no.niths.domain.development.Application;
import no.niths.services.development.interfaces.ApplicationService;
import no.niths.services.interfaces.GenericService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller for handling applications
 * 
 */
@Controller
@RequestMapping(DomainConstantNames.APPLICATIONS)
public class ApplicationControllerImpl extends
		AbstractRESTControllerImpl<Application> implements
		ApplicationController {

	@Autowired
	private ApplicationService service;

	private ApplicationList applicationList = new ApplicationList();

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void create(
	        @RequestBody Application domain,
	        HttpServletResponse res) {
		super.create(domain, res);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void delete(@PathVariable long id) {
		super.delete(id);
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * Disables an application
	 * 
	 * @param applicationId id of the application
	 * @throws ObjectNotFoundException if no application is found
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	@RequestMapping(value = { "{applicationId}/disable" }, method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Application disable")
	public void disableApplication(@PathVariable Long applicationId){
		service.disableApplication(applicationId);
	}
	
	/**
	 * Returns a list applications ordered
	 * by the number of requests @See {@link Application}
	 * 
	 * @param maxResults number of results
	 * @return list with maxResults applications
	 */
	@Override
	@RequestMapping(value = {"top/{maxResults}"}, method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public List<Application> getTopApps(@PathVariable int maxResults){
		renewList(service.getTopApps(maxResults));
		return applicationList;
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

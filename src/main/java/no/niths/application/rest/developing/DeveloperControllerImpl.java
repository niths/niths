package no.niths.application.rest.developing;

import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.developing.interfaces.DeveloperController;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.lists.DeveloperList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.AppNames;
import no.niths.common.SecurityConstants;
import no.niths.domain.developing.Developer;
import no.niths.services.auth.interfaces.KeyGeneratorService;
import no.niths.services.developing.interfaces.DeveloperService;
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
 * Controller for handling developers and their applications
 * 
 */
@Controller
@RequestMapping(AppNames.DEVELOPERS)
public class DeveloperControllerImpl extends
		AbstractRESTControllerImpl<Developer> implements DeveloperController {

	@Autowired
	private DeveloperService service;
	
	@Autowired
	private KeyGeneratorService keyService;
	
	private DeveloperList developerList = new DeveloperList();

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void create(@RequestBody Developer domain, HttpServletResponse res) {
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
	public void update(@RequestBody Developer domain) {
		super.update(domain);
	}
	
	/**
	 * Enables a developer
	 * <p>
	 * Developer must be enabled to do request
	 * <p>
	 * @param developerId id of the developer
	 * @throws ObjectNotFoundException if no developer is found
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	@RequestMapping(value = { "{developerId}/enable" }, method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Developer enabled")
	public void enableDeveloper(@PathVariable Long developerId){
		service.enableDeveloper(developerId);
	}

	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	@RequestMapping(value = { "{developerId}/disable" }, method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Developer diabled")
	public void disableDeveloper(@PathVariable Long developerId){
		service.disableDeveloper(developerId);
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	@RequestMapping(value = { "{developerId}/resetDeveloperKey" }, method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Application added to developer")
	public void resetDeveloperKey(@PathVariable Long developerId){
		service.resetDeveloperKey(developerId,keyService.generateDeveloperKey());		
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	@RequestMapping(value = { "{developerId}/add/application/{applicationId}" }, method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Application added to developer")
	public void addApplication(@PathVariable Long developerId,@PathVariable Long applicationId) {
		service.addApplication(developerId,applicationId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	@RequestMapping(value = { "{developerId}/remove/application/{applicationId}" }, method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Application removed from developer")
	public void removeApplication(@PathVariable Long developerId,@PathVariable Long applicationId) {
		service.removeApplicaiton(developerId,applicationId);
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

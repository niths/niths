package no.niths.application.rest;

import java.util.ArrayList;

import no.niths.application.rest.exception.NotInCollectionException;
import no.niths.application.rest.interfaces.DeveloperController;
import no.niths.application.rest.lists.DeveloperList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.AppConstants;
import no.niths.common.SecurityConstants;
import no.niths.common.ValidationHelper;
import no.niths.domain.Application;
import no.niths.domain.Developer;
import no.niths.services.interfaces.ApplicationService;
import no.niths.services.interfaces.DeveloperService;
import no.niths.services.interfaces.GenericService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(AppConstants.DEVELOPERS)
public class DeveloperControllerImpl extends
		AbstractRESTControllerImpl<Developer> implements DeveloperController {

	private static final Logger logger = LoggerFactory
			.getLogger(DeveloperControllerImpl.class);

	@Autowired
	private DeveloperService service;

	@Autowired
	private ApplicationService appService;
	
	private DeveloperList developerList = new DeveloperList();

	@Override
	public ArrayList<Developer> getAll(Developer domain) {
		developerList = (DeveloperList) super.getAll(domain);
		for (Developer d : developerList) {
			d.setApps(null);
		}
		return developerList;
	}

	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void create(@RequestBody Developer domain) {
		super.create(domain);
	}


	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void hibernateDelete(@PathVariable long id) {
		super.hibernateDelete(id);
	}

	@Override
	// @PreAuthorize("(hasAnyRole('ROLE_ADMIN', 'ROLE_SR')) or (principal.developerId == #domain.id)")
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void update(@RequestBody Developer domain) {
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

	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	@RequestMapping(value = { "add/application/{devId}/{appId}" }, method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Application added to developer")
	public void addApp(@PathVariable Long devId,@PathVariable Long appId) {
		Developer developer = service.getById(devId);
		ValidationHelper.isObjectNull(developer, "Developer not found");

		Application app = appService.getById(appId);
		ValidationHelper.isObjectNull(app, "Application not found");

		developer.getApps().add(app);
		service.update(developer);
		logger.debug("developer adde a new app:"+ app.getTitle());
	}

	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	@RequestMapping(value = { "remove/application/{devId}/{appId}" }, method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Application removed from developer")
	public void removeApp(@PathVariable Long devId,@PathVariable Long appId) {
		Developer developer = service.getById(devId);
		ValidationHelper.isObjectNull(developer, "Developer not found");

		boolean isRemoved = false;
		for (int i = 0; i < developer.getApps().size(); i++) {
			if (developer.getApps().get(i).getId() == appId) {
				developer.getApps().remove(i);
				isRemoved = true;
			}
		}

		if (isRemoved) {
			service.update(developer);
			logger.debug("app removed");
		} else {
			throw new NotInCollectionException(
					"App does not belong to developer");
		}
	}

}

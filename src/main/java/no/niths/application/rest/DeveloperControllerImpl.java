package no.niths.application.rest;

import java.util.ArrayList;

import no.niths.application.rest.interfaces.DeveloperController;
import no.niths.application.rest.lists.DeveloperList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.AppConstants;
import no.niths.common.SecurityConstants;
import no.niths.domain.Developer;
import no.niths.services.interfaces.DeveloperService;
import no.niths.services.interfaces.GenericService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

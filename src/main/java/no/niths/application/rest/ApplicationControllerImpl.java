package no.niths.application.rest;

import java.util.ArrayList;

import no.niths.application.rest.interfaces.ApplicationController;
import no.niths.application.rest.lists.ApplicationList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.AppConstants;
import no.niths.common.SecurityConstants;
import no.niths.domain.Application;
import no.niths.services.interfaces.ApplicationService;
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
 * Controller for handling applications
 *
 */
@Controller
@RequestMapping(AppConstants.APPLICATIONS)
public class ApplicationControllerImpl extends AbstractRESTControllerImpl<Application> implements ApplicationController{

	private static final Logger logger = LoggerFactory
			.getLogger(ApplicationControllerImpl.class);

	@Autowired
	private ApplicationService service;

	private ApplicationList applicationList = new ApplicationList();
	
	@Override
	public ArrayList<Application> getAll(Application domain) {
		applicationList = (ApplicationList) super.getAll(domain);
		return applicationList;
	}
	
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void create(@RequestBody Application domain) {
		super.create(domain);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void delete(@PathVariable Long id) {
		super.delete(id);
	}
	
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void hibernateDelete(@PathVariable long id) {
		super.hibernateDelete(id);
	}
	
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void update(@RequestBody Application domain) {
		super.update(domain);
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

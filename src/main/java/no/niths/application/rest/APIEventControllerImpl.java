package no.niths.application.rest;

import no.niths.application.rest.interfaces.APIEventController;
import no.niths.application.rest.lists.APIEventList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.AppConstants;
import no.niths.common.SecurityConstants;
import no.niths.domain.APIEvent;
import no.niths.services.interfaces.APIEventService;
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
 * Controller for handling API events
 *
 */
@Controller
@RequestMapping(AppConstants.API_EVENTS)
public class APIEventControllerImpl extends AbstractRESTControllerImpl<APIEvent> implements APIEventController{

	private static final Logger logger = LoggerFactory
			.getLogger(APIEventControllerImpl.class);

	@Autowired
	private APIEventService service;

	private APIEventList subjectList = new APIEventList();
	
	@Override
	@PreAuthorize(SecurityConstants.ONLY_ADMIN)
	public void create(@RequestBody APIEvent domain) {
		super.create(domain);
		logger.debug("APIEvent created with id: " + domain.getId());
	}

	@Override
	@PreAuthorize(SecurityConstants.ONLY_ADMIN)
	public void hibernateDelete(@PathVariable long id) {
		super.hibernateDelete(id);
	}
	
	@Override
	@PreAuthorize(SecurityConstants.ONLY_ADMIN)
	public void update(@RequestBody APIEvent domain) {
		super.update(domain);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GenericService<APIEvent> getService() {
		return service;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListAdapter<APIEvent> getList() {
		return subjectList;
	}


}

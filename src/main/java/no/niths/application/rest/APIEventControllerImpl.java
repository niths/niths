package no.niths.application.rest;

import no.niths.application.rest.interfaces.APIEventController;
import no.niths.application.rest.lists.APIEventList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.AppNames;
import no.niths.domain.APIEvent;
import no.niths.services.interfaces.APIEventService;
import no.niths.services.interfaces.GenericService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Controller for handling API events
 *
 */
@Controller
@RequestMapping(AppNames.APIEVENTS)
public class APIEventControllerImpl extends AbstractRESTControllerImpl<APIEvent> implements APIEventController{

	@Autowired
	private APIEventService service;

	private APIEventList subjectList = new APIEventList();
	
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

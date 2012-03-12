package no.niths.application.rest;

import no.niths.application.rest.interfaces.APIEventController;
import no.niths.application.rest.interfaces.SubjectController;
import no.niths.application.rest.lists.APIEventList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.SubjectList;
import no.niths.common.AppConstants;
import no.niths.domain.APIEvent;
import no.niths.domain.Subject;
import no.niths.services.interfaces.APIEventService;
import no.niths.services.interfaces.GenericService;
import no.niths.services.interfaces.SubjectService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Controller for subjects
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

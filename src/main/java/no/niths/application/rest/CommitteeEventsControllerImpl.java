package no.niths.application.rest;

import no.niths.application.rest.interfaces.CommitteeEventsController;
import no.niths.application.rest.lists.CommitteeEventList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.AppConstants;
import no.niths.domain.CommitteeEvent;
import no.niths.services.interfaces.CommitteeEventsService;
import no.niths.services.interfaces.GenericService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AppConstants.EVENTS)
public class CommitteeEventsControllerImpl extends
		AbstractRESTControllerImpl<CommitteeEvent>
		implements CommitteeEventsController {

	@Autowired
	private CommitteeEventsService service;
	
	private static final Logger logger = LoggerFactory
			.getLogger(CommitteeEventsControllerImpl.class);
	
	private CommitteeEventList committeeEventList = new CommitteeEventList();
	
	@Override
	public GenericService<CommitteeEvent> getService() {
		return service;
	}

	@Override
	public ListAdapter<CommitteeEvent> getList() {
		return committeeEventList;
	}
}

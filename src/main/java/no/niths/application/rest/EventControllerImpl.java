package no.niths.application.rest;

import java.util.List;

import no.niths.application.rest.interfaces.EventController;
import no.niths.application.rest.lists.EventList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.AppConstants;
import no.niths.common.ValidationHelper;
import no.niths.domain.Event;
import no.niths.services.interfaces.EventsService;
import no.niths.services.interfaces.GenericService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for events
 */
@Controller
@RequestMapping(AppConstants.EVENTS)
public class EventControllerImpl extends AbstractRESTControllerImpl<Event>
		implements EventController {

	@Autowired
	private EventsService service;

	private static final Logger logger = LoggerFactory
			.getLogger(EventControllerImpl.class);

	private EventList eventList = new EventList();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GenericService<Event> getService() {
		return service;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListAdapter<Event> getList() {
		return eventList;
	}

	@Override
	@RequestMapping(value = { "tag={tag}" }, method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public List<Event> getEventsByTag(@PathVariable String tag) {
		eventList.clear();
		eventList.addAll(service.getEventsByTag(tag));
		eventList.setData(eventList);
		ValidationHelper.isListEmpty(eventList);
		return eventList;
	}
}

package no.niths.application.rest.school;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import no.niths.aop.ApiEvent;
import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.RESTConstants;
import no.niths.application.rest.helper.TagProvider;
import no.niths.application.rest.helper.TimeDTO;
import no.niths.application.rest.lists.EventList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.school.interfaces.EventController;
import no.niths.common.constants.DomainConstantNames;
import no.niths.common.constants.SecurityConstants;
import no.niths.common.helpers.ValidationHelper;
import no.niths.domain.school.Event;
import no.niths.services.interfaces.GenericService;
import no.niths.services.school.interfaces.EventsService;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller for events
 */
@Controller
@RequestMapping(DomainConstantNames.EVENTS)
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
	@PreAuthorize(SecurityConstants.ALL_LEADERS)
	@ApiEvent(title="Event created")
	public void create(@RequestBody Event domain, HttpServletResponse res) {
		super.create(domain, res);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ALL_LEADERS)
	@ApiEvent(title="Event updated")
	public void update(@RequestBody Event domain) {
		super.update(domain);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@PreAuthorize(SecurityConstants.ALL_LEADERS)
	@ApiEvent(title="Event removed")
	public void delete(@PathVariable long id) {
		super.delete(id);
	}

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

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(value = { "search" }, 	
	method = RequestMethod.GET, 
	headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public List<Event> getEventsByTag(TagProvider tag) {
		logger.debug(tag+"");
		renewList(service.getEventsByTag(tag+""));	
		return eventList;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(
	        value  = "{eventId}/location/{locId}",
	        method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK, reason = "Location Added")
	public void addLocation(
	        @PathVariable Long eventId,
	        @PathVariable Long locId) {
		
		service.addLocation(eventId,locId);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(
	        value  = "{eventId}/location/{locId}",
	        method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK, reason = "Location removed")
	public void removeLocation(
	        @PathVariable Long eventId,
	        @PathVariable Long locId) {
		
		service.removeLocation(eventId,locId);
	}
	
	@Override
	@RequestMapping(value = "dates", method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public List<Event> getEventsBetweenDates(TimeDTO timeDTO) {
		logger.debug(timeDTO +"");
		ValidationHelper.isObjectNull(timeDTO.getStartTime());
		
		if(timeDTO.getEndTime() != null){
			renewList(service.getEventsBetweenDates(timeDTO.getStartTimeCal(), timeDTO.getEndTimeCal()));
		}else{
			renewList(service.getEventsBetweenDates(timeDTO.getStartTimeCal(), null));
		}
		return eventList;
	}

	@Override
	@RequestMapping(value = "tags-and-dates", method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public List<Event> getEventsBetweenDatesAndByTag(TagProvider tag) {
		if(tag.getEndTime() != null){
			renewList(service.getEventsBetweenDatesAndByTag(tag+"",tag.getStartTimeCal(), tag.getEndTimeCal()));
		}else if(tag.getStartTime() != null){
			renewList(service.getEventsBetweenDatesAndByTag(tag+" ",tag.getStartTimeCal(), null));
		}else{
			eventList.clear();
			renewList(eventList);
		}
		return eventList;
	}
}

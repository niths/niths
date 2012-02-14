package no.niths.application.rest;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElementWrapper;

import no.niths.application.rest.lists.CommitteeEventList;
import no.niths.common.AppConstants;
import no.niths.domain.CommitteeEvent;
import no.niths.services.CommitteeEventsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(AppConstants.EVENTS)
public class CommitteeEventsController implements
		RESTController<CommitteeEvent> {

	private static final Logger logger = LoggerFactory
			.getLogger(CommitteeEventsController.class);
	
	@Autowired
	private CommitteeEventsService service;

	private CommitteeEventList list = new CommitteeEventList();

	@Override
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED, reason = "Committe created")
	public void create(@RequestBody CommitteeEvent event) {
	
		service.create(event);
	}

	@Override
	@RequestMapping(value = { "{id}" }, method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public CommitteeEvent getById(@PathVariable Long id) {
		
		return service.getCommitteeEventsById(id);
			
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public ArrayList<CommitteeEvent> getAll(CommitteeEvent committeeEvent,
			HttpEntity<byte[]> request) {

		final String FIRST = request.getHeaders()
				.getFirst(RESTConstants.ACCEPT);

		if (FIRST.equals(RESTConstants.JSON)) {
			return (ArrayList<CommitteeEvent>) service.getAll(committeeEvent);
		} else if (FIRST.equals(RESTConstants.XML)) {
			list.setEventData(service.getAll(committeeEvent));
			return list;
		}

		return null;
	}

	/**
	 * 
	 * @param Course
	 *            The Course to update
	 */
	@Override
	@RequestMapping(value = { "{id}" }, method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK,reason = "Update ok")
	public void update(@RequestBody CommitteeEvent event, @PathVariable Long id) {

		// If the ID is only provided through the URL.
		if (id != null)
			event.setId(id);

		service.update(event);
	}

	/**
	 * 
	 * @param long The id of the Course to delete
	 */
	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK, reason ="CommitteeEvent deleted")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}

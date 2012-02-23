package no.niths.application.rest.interfaces;

import java.util.List;

import no.niths.domain.Event;
/**
 * Controller for events
 */
public interface EventController extends GenericRESTController<Event> {
	public List<Event> getEventsByTag(String tag);
}

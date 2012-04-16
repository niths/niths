package no.niths.application.rest.interfaces;

import java.util.List;

import no.niths.application.rest.helper.TagProvider;
import no.niths.application.rest.helper.TimeDTO;
import no.niths.domain.Event;
/**
 * Controller for events
 */
public interface EventController extends GenericRESTController<Event> {
	
	/**
	 * 
	 * Returns a list of events on the given tag.
	 * Here you can search on more tags by adding &
	 * between the search words like this:
	 * tag = "party&fun"
	 * @param tag
	 * @return
	 */
	List<Event> getEventsByTag(TagProvider tag);
	
	/**
	 * Adds a location to a event
	 * @param eventId
	 * @param LocId
	 */
	void addLocation(Long eventId, Long LocId);
	
	/**
	 * Removes a location from a event
	 * @param eventId
	 * @param LocId
	 */
	void removeLocation(Long eventId, Long LocId);
	
	
	/**
	 * Returns events between or from timeDTO's startTime or / and endTime
	 * @param timeDTO
	 * @return
	 */
	List<Event> getEventsBetweenDates(TimeDTO timeDTO);
}

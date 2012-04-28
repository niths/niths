package no.niths.application.rest.school.interfaces;

import java.util.List;

import no.niths.application.rest.helper.TagProvider;
import no.niths.application.rest.helper.TimeDTO;
import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.school.Event;
/**
 * Controller for events
 */
public interface EventController extends GenericRESTController<Event> {
	
	/**
	 * 
	 * Returns a list of events on the given tag.
	 * Here you can search on more tags by adding ,
	 * between the search words like this:
	 * tag = "party,fun"
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
	 * Convenience method for changing a location
	 * @param eventId the event of which the location will be changed
	 * @param locId the id of the new location
	 */
	void changeLocation(Long eventId, Long locId);
	
	/**
	 * Removes a location from a event
	 * @param eventId
	 * @param LocId
	 */
	void removeLocation(Long eventId);
	
	
	/**
	 * Returns events between or from timeDTO's startTime or / and endTime
	 * @param timeDTO
	 * @return
	 */
	List<Event> getEventsBetweenDates(TimeDTO timeDTO);
	
	/**
	 * Returns events that starts between start and end time provide and tags,
	 *  
	 * @param tag
	 * @return
	 */
	List<Event> getEventsBetweenDatesAndByTag(TagProvider tag);
}

package no.niths.application.rest.school.interfaces;

import java.util.List;

import no.niths.application.rest.helper.TagProvider;
import no.niths.application.rest.helper.TimeDTO;
import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.school.Event;
/**
 * Controller for event
 * has the basic CRUD methods and
 * methods too add, change and remove location
 * in addition too methods for getEventsByTag,
 * getEventsBetweenDates
 * and getEventsBetweenDatesAndByTag
 *
 * For the URL too get Event add /events
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
public interface EventController extends GenericRESTController<Event> {
	
	/**
	 * Returns a list of events on the given tag.
	 * Here you can search on more tags by adding ,
	 * between the search words like this:
	 * tag = "party,fun"
     *
     * Too get events by tag add /search
     * too the URL
     *
     * Use the GET method
     *
	 * @param tag search word that the event should contain
	 * @return a list with events
	 */
	List<Event> getEventsByTag(TagProvider tag);
	
	/**
	 * Adds a location to a event
     *
     * Too add location add /{eventId}/location/{locId}
     * too the URL
     *
     * Use the POST method
     *
	 * @param eventId id for event
	 * @param locId id for location
	 */
	void addLocation(Long eventId, Long locId);

	/**
	 * Convenience method for changing a location
     *
     * Too change location add /{eventId}/location/{locId}
     * too the URL
     *
     * Use the PUT method
     *
	 * @param eventId the event of which the location will be changed
	 * @param locId the id of the new location
	 */
	void changeLocation(Long eventId, Long locId);
	
	/**
	 * Removes a location from a event
     *
     * Too remove location add /{eventId}/location
     * too the URL
     *
     * Use the DELETE method
     *
	 * @param eventId id for event
	 */
	void removeLocation(Long eventId);
	
	
	/**
	 * Returns events between or from timeDTO's startTime or / and endTime
     *
     * Too get all events between dates add /dates
     * too the URL
     *
     * Use the GET method
     *
	 * @param timeDTO dates that events should be between
	 * @return a list with events
	 */
	List<Event> getEventsBetweenDates(TimeDTO timeDTO);
	
	/**
	 * Returns events that starts between start and end time provide and tags,
     *
     * Too get all events with tag and after date add /tags-and-dates
     * too the URL
     *
     * Use the GET method
	 *  
	 * @param tag contains search word and dates
	 * @return a list with events
	 */
	List<Event> getEventsBetweenDatesAndByTag(TagProvider tag);
}

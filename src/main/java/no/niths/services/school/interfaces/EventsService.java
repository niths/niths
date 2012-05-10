package no.niths.services.school.interfaces;

import java.util.GregorianCalendar;
import java.util.List;

import no.niths.domain.school.Event;
import no.niths.services.interfaces.GenericService;
/**
 * Service Class for Event
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for getEventsByTag, getEventsBetweenDates,
 * getEventsBetweenDatesAndByTag,
 * addLocation and removeLocation
 * </p>
 */
public interface EventsService extends GenericService<Event> {

    /**
     * Returns all events with the tag
     * @param tag a String too specify which events should be returned
     * @return list with events
     */
    List<Event> getEventsByTag(String tag);

    /**
     * Returns all events between two dates
     * @param startTime a date which events should be after
     * @param endTime a date which events should be before
     * @return list with events
     */
    List<Event> getEventsBetweenDates(GregorianCalendar startTime, GregorianCalendar endTime);

    /**
     * Adds a location to the Event
     * @param eventId id for event
     * @param locId id for location
     */
    void addLocation(Long eventId, Long locId);

    /**
     * Removes a location from an event
     * @param eventId id for event
     */
    void removeLocation(Long eventId);

    /**
     * Returns all events which are between dates and has tag
     * @param tag a String too specify which events should be returned
     * @param startTime a date which events should be after
     * @param endTime a date which events should be before
     * @return list with events
     */
    List<Event> getEventsBetweenDatesAndByTag(String tag,
            GregorianCalendar startTime, GregorianCalendar endTime);

}
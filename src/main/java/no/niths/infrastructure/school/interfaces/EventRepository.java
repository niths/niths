package no.niths.infrastructure.school.interfaces;

import java.util.GregorianCalendar;
import java.util.List;

import no.niths.domain.school.Event;
import no.niths.infrastructure.interfaces.GenericRepository;

/**
 * Repository class for Event
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for getEventsByTag, getEventsBetweenDates
 * and getEventsBetweenDatesAndByTag
 * </p>
 */
public interface EventRepository extends GenericRepository<Event> {

    /**
     * <p>
     * Returns all events with matching tags
     * </p>
     * @param tag comma separated string, ex: fadderuka,kroa,nith
     * @return list with events matching tags
     */
    List<Event> getEventsByTag(String tag);
    
    /**
     * <p>
     * Returns all events with start date between the two dates
     * provided as parameters 
     * </p>
     * @param startTime 
     * @param endTime
     * @return List of events with startdate between the param dates
     */
    List<Event> getEventsBetweenDates(GregorianCalendar startTime, GregorianCalendar endTime);
    
    /**
     * <p>
     * Returns all events with start date between the two dates provided
     * as parameters and with matching tags.
     * </p>
     * @param tag Comma separated string
     * @param startTime
     * @param endTime
     * @return list with events matching the params
     */
    List<Event> getEventsBetweenDatesAndByTag(String tag,GregorianCalendar startTime, GregorianCalendar endTime);
}
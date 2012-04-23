package no.niths.services.school.interfaces;

import java.util.GregorianCalendar;
import java.util.List;

import no.niths.domain.school.Event;
import no.niths.services.interfaces.GenericService;

public interface EventsService extends GenericService<Event> {

	List<Event> getEventsByTag(String tag);
	
	List<Event> getEventsBetweenDates(GregorianCalendar startTime, GregorianCalendar endTime);

	void addLocation(Long eventId, Long locId);
	
	void removeLocation(Long eventId, Long locId);
	
	List<Event> getEventsBetweenDatesAndByTag(String tag,
			GregorianCalendar startTime, GregorianCalendar endTime);

}
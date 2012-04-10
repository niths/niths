package no.niths.services.interfaces;

import java.util.GregorianCalendar;
import java.util.List;

import no.niths.domain.Event;

public interface EventsService extends GenericService<Event> {

	List<Event> getEventsByTag(String tag);
	
	List<Event> getEventsBetweenDates(GregorianCalendar startTime, GregorianCalendar endTime);

}
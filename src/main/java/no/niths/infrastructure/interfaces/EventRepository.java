package no.niths.infrastructure.interfaces;

import java.util.GregorianCalendar;
import java.util.List;

import no.niths.domain.Event;


public interface EventRepository extends GenericRepository<Event> {

	List<Event> getEventsByTag(String tag);
	
	List<Event> getEventsBetweenDates(GregorianCalendar startTime, GregorianCalendar endTime);
}
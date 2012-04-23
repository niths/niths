package no.niths.infrastructure.school.interfaces;

import java.util.GregorianCalendar;
import java.util.List;

import no.niths.domain.school.Event;
import no.niths.infrastructure.interfaces.GenericRepository;


public interface EventRepository extends GenericRepository<Event> {

	List<Event> getEventsByTag(String tag);
	
	List<Event> getEventsBetweenDates(GregorianCalendar startTime, GregorianCalendar endTime);
	
	List<Event> getEventsBetweenDatesAndByTag(String tag,GregorianCalendar startTime, GregorianCalendar endTime);
}
package no.niths.services.interfaces;

import java.util.List;

import no.niths.domain.Event;

public interface EventsService extends GenericService<Event> {

	public List<Event> getEventsByTag(String tag);
}

package no.niths.infrastructure.interfaces;

import java.util.List;

import no.niths.domain.Event;


public interface EventRepositorty extends GenericRepository<Event> {

	public List<Event> getEventsByTag(String tag);
}

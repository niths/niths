package no.niths.infrastructure;

import no.niths.domain.Event;
import no.niths.infrastructure.interfaces.EventRepositorty;

import org.springframework.stereotype.Repository;

@Repository
public class EventRepositoryImpl extends
		GenericRepositoryImpl<Event> implements
		EventRepositorty {

	public EventRepositoryImpl() {
		super(Event.class);
	}

}

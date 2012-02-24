package no.niths.services;

import java.util.List;

import no.niths.domain.Event;
import no.niths.infrastructure.interfaces.EventRepositorty;
import no.niths.services.interfaces.EventsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventServiceImpl implements EventsService {
	private static final Logger logger = LoggerFactory
			.getLogger(EventServiceImpl.class);

	@Autowired
	private EventRepositorty repo;

	public Long create(Event committeeEvents) {
		return repo.create(committeeEvents);
	}

	public List<Event> getAll(Event event) {
		List<Event> events = repo.getAll(event);
		return events;
	}

	public Event getById(long id) {
		Event event = repo.getById(id);
		return event;
	}

	public void update(Event committeeEvents) {
		repo.update(committeeEvents);

	}

	public boolean delete(long eid) {
		return repo.delete(eid);
	}

	@Override
	public List<Event> getEventsByTag(String tag) {
		List<Event> events = repo.getEventsByTag(tag);
		return events;
	}
}

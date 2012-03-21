package no.niths.services;

import java.util.List;

import no.niths.aop.ApiEvent;
import no.niths.domain.Event;
import no.niths.infrastructure.interfaces.EventRepository;
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
	private EventRepository repo;

	@ApiEvent(title = "Event created")
	public Long create(Event committeeEvents) {
		logger.debug("comitteeEvents"+committeeEvents);
		return repo.create(committeeEvents);
	}

	public List<Event> getAll(Event event) {
		List<Event> events = repo.getAll(event);
		return events;
	}

	public Event getById(long id) {
		Event event = repo.getById(id);

		if (event != null) {
			if(event.getLocation() != null){
				event.getLocation().getPlace();
			}
		}
		return event;
	}

	@ApiEvent(title = "Event updated")
	public void update(Event committeeEvents) {
		repo.update(committeeEvents);

	}

	@ApiEvent(title = "Event deleted")
	public boolean delete(long eid) {
		return repo.delete(eid);
	}

	@Override
	public List<Event> getEventsByTag(String tag) {
		List<Event> events = repo.getEventsByTag(tag);
		return events;
	}

	@Override
	@ApiEvent(title = "Event deleted")
	public void hibernateDelete(long id) {
		repo.hibernateDelete(id);
	}
}

package no.niths.services;

import java.util.List;

import no.niths.domain.Event;
import no.niths.infrastructure.interfaces.EventRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.interfaces.EventsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl extends AbstractGenericService<Event> implements
		EventsService {

	@Override
	public Event getById(long id) {
		Event event = super.getById(id);
		if (event != null) {
			if (event.getLocation() != null) {
				event.getLocation().getLatitude();
			}
		}
		return event;
	}

	@Autowired
	private EventRepository repo;

	@Override
	public List<Event> getEventsByTag(String tag) {
		return repo.getEventsByTag(tag);
	}

	@Override
	public GenericRepository<Event> getRepository() {
		return repo;
	}

}

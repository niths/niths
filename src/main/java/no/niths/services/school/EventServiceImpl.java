package no.niths.services.school;

import java.util.GregorianCalendar;
import java.util.List;

import no.niths.application.rest.exception.ObjectInCollectionException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.helper.Error;
import no.niths.common.LazyFixer;
import no.niths.common.MessageProvider;
import no.niths.common.ValidationHelper;
import no.niths.domain.location.Location;
import no.niths.domain.school.Event;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.location.interfaces.LocationRepository;
import no.niths.infrastructure.school.interfaces.EventRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.school.interfaces.EventsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl extends AbstractGenericService<Event> implements
		EventsService {
	private Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

	@Autowired
	private EventRepository repo;

	private LazyFixer<Event> fixer = new LazyFixer<Event>();

	@Autowired
	private LocationRepository locationRepo;

	@Override
	public List<Event> getEventsByTag(String tag) {
		List<Event> events = repo.getEventsByTag(tag);
		fixer.fetchChildren(events);
		return events;
	}

	@Override
	public GenericRepository<Event> getRepository() {
		return repo;
	}

	@Override
	public List<Event> getEventsBetweenDates(GregorianCalendar startTime,
			GregorianCalendar endTime) {
		List<Event> events = repo.getEventsBetweenDates(startTime, endTime);
		fixer.fetchChildren(events);
		return events;
	}

	@Override
	public void addLocation(Long eventId, Long locId) {
		Event event = super.getById(eventId);
		ValidationHelper.isObjectNull(event, Event.class);

		if (event.getLocation() != null && event.getLocation().getId() == locId) {
			logger.debug("location exist");
			throw new ObjectInCollectionException(
					MessageProvider.buildErrorMsg(Location.class,
							Error.OBJECT_IN_COLLECTION));
		}

		Location location = locationRepo.getById(locId);
		ValidationHelper.isObjectNull(location, Location.class);
		event.setLocation(location);
		logger.debug("Location added to event");
	}

	@Override
	public void removeLocation(Long eventId, Long locId) {
		Event event = super.getById(eventId);
		ValidationHelper.isObjectNull(event, Event.class);
		
		if (event.getLocation() != null && event.getLocation().getId() == locId) {
			event.setLocation(null);
		}else{
			logger.debug("Event not Found");
			throw new ObjectNotFoundException(MessageProvider.buildErrorMsg(
					Location.class,
					Error.DOES_NOT_EXIST));
		}
	}

}

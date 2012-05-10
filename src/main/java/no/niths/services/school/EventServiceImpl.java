package no.niths.services.school;

import java.util.GregorianCalendar;
import java.util.List;

import no.niths.application.rest.exception.InvalidValueException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.helper.Error;
import no.niths.application.rest.helper.Status;
import no.niths.common.helpers.LazyFixer;
import no.niths.common.helpers.MessageProvider;
import no.niths.common.helpers.ValidationHelper;
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
/**
 * Service Class for Event
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for getEventsByTag, getEventsBetweenDates,
 * getEventsBetweenDatesAndByTag,
 * addLocation and removeLocation
 * </p>
 */
@Service
public class EventServiceImpl extends AbstractGenericService<Event> implements
        EventsService {
    private Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

    @Autowired
    private EventRepository repo;

    private LazyFixer<Event> fixer = new LazyFixer<Event>();

    @Autowired
    private LocationRepository locationRepo;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Event> getEventsByTag(String tag) {
        List<Event> events = repo.getEventsByTag(tag);
        fixer.fetchChildren(events);
        return events;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GenericRepository<Event> getRepository() {
        return repo;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Long create(Event domain) {
        validateStartAndEndTime(domain);
        return super.create(domain);
    }

    /**
     * Method for validating if startTime is larger than time now, 
     * and if its large than end time. Endtime is allowed to be null 
     * @param domain
     */
    private void validateStartAndEndTime(Event domain) {
        GregorianCalendar now = new GregorianCalendar();
        if(domain!=null){
            System.err.println("is not null");
            // if startTime not equal to null
            if(domain.getStartTime() !=null){

                if(domain.getStartTime().compareTo(now) >= 0){
                    if(domain.getEndTime()!= null){
                        System.err.println(domain.getStartTime().compareTo(domain.getEndTime()) + " start time vs end time");
                        if(domain.getStartTime().compareTo(domain.getEndTime()) >= 0){
                            throw new InvalidValueException("Star time has to be less than end time");
                        }
                    }
                }else {
                    throw new InvalidValueException("Start time must be large than the time now " + now.getTime().toString());
                }
            }else{
                throw new InvalidValueException("Star time cannot be null");
            }
        }
    }
    
    @Override
    public void mergeUpdate(Event domain) {
        System.err.println(domain.toString());
        validateStartAndEndTime(domain);
        super.mergeUpdate(domain);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Event> getEventsBetweenDates(GregorianCalendar startTime,
            GregorianCalendar endTime) {
        List<Event> events = repo.getEventsBetweenDates(startTime, endTime);
        fixer.fetchChildren(events);
        return events;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addLocation(Long eventId, Long locId) {
        Event event = super.getById(eventId);
        ValidationHelper.isObjectNull(event, Event.class);

        checkIfObjectExists(event.getLocation(), locId, Location.class);

        Location location = locationRepo.getById(locId);
        ValidationHelper.isObjectNull(location, Location.class);

        event.setLocation(location);
        logger.debug(MessageProvider
                .buildStatusMsg(Event.class, Status.UPDATED));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeLocation(Long eventId) {
        Event event = super.getById(eventId);
        ValidationHelper.isObjectNull(event, Event.class);

        if (event.getLocation() != null) {
            event.setLocation(null);
        } else {
            String msg = MessageProvider.buildErrorMsg(Location.class,
                    Error.DOES_NOT_EXIST);
            logger.debug(msg);
            throw new ObjectNotFoundException(msg);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Event> getEventsBetweenDatesAndByTag(String tag,
            GregorianCalendar startTime, GregorianCalendar endTime) {
        return repo.getEventsBetweenDatesAndByTag(tag, startTime, endTime);
    }

}

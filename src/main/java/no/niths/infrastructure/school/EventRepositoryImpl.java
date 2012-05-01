package no.niths.infrastructure.school;

import java.util.GregorianCalendar;
import java.util.List;

import no.niths.domain.school.Event;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.QueryGenerator;
import no.niths.infrastructure.school.interfaces.EventRepository;

import org.springframework.stereotype.Repository;
/**
 * Repository class for Event
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for getEventsByTag, getEventsBetweenDates
 * and getEventsBetweenDatesAndByTag
 * </p>
 */
@Repository
public class EventRepositoryImpl extends AbstractGenericRepositoryImpl<Event>
		implements EventRepository {

	private static final String START_TIME = "startTime";
	private QueryGenerator<Event> queryGen;
	private final String COLUMNAME = "tags";


	public EventRepositoryImpl() {
		super(Event.class, new Event());
		queryGen = new QueryGenerator<Event>(Event.class);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public List<Event> getEventsByTag(String tag) {
		return queryGen.whereQuery(tag, COLUMNAME, getSession()
				.getCurrentSession());
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public List<Event> getEventsBetweenDates(GregorianCalendar startTime,
			GregorianCalendar endTime) {
		return queryGen.getBetweenDates(startTime, endTime, getSession().getCurrentSession());
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public List<Event> getEventsBetweenDatesAndByTag(String tag,
			GregorianCalendar startTime, GregorianCalendar endTime) {
		return queryGen.whereAndBetween(tag, COLUMNAME,START_TIME, getSession().getCurrentSession(), startTime, endTime);
	}
}

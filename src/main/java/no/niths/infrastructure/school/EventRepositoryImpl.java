package no.niths.infrastructure.school;

import java.util.GregorianCalendar;
import java.util.List;

import no.niths.domain.school.Event;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.QueryGenerator;
import no.niths.infrastructure.school.interfaces.EventRepository;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private Logger logger = LoggerFactory.getLogger(Event.class);

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
	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getEventsBetweenDates(GregorianCalendar startTime,
			GregorianCalendar endTime) {
		
		Criteria crit = getSession().getCurrentSession().createCriteria(Event.class);
		
	
		boolean isEndTimeNull = endTime == null;
		if (isEndTimeNull) {
			crit.add(Restrictions.ge(START_TIME, startTime));

		} else if (startTime !=  null){
			crit.add(Restrictions.between(START_TIME, startTime,endTime));
		}
		
		crit.addOrder(Order.asc(START_TIME));	
		
		logger.debug(crit.toString());

		return crit.list();

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

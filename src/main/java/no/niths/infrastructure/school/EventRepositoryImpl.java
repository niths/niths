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
	 * <p>
	 * Returns all events with matching tags
	 * </p>
	 * @param tag comma separated string, ex: fadderuka,kroa,nith
	 * @return list with events matching tags
	 */
	@Override
	public List<Event> getEventsByTag(String tag) {
		return queryGen.whereQuery(tag, COLUMNAME, getSession()
				.getCurrentSession());
	}

	/**
	 * <p>
	 * Returns all events with start date between the two dates
	 * provided as parameters 
	 * </p>
	 * @param startTime 
	 * @param endTime
	 * @return List of events with startdate between the param dates
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
	 * <p>
	 * Returns all events with start date between the two dates provided
	 * as parameters and with matching tags.
	 * </p>
	 * @param tag Comma separated string
	 * @param startTime
	 * @param endTime
	 * @return list with events matching the params
	 */
	@Override
	public List<Event> getEventsBetweenDatesAndByTag(String tag,
			GregorianCalendar startTime, GregorianCalendar endTime) {
		return queryGen.whereAndBetween(tag, COLUMNAME,START_TIME, getSession().getCurrentSession(), startTime, endTime);
	}
}

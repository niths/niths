package no.niths.infrastructure;

import java.util.GregorianCalendar;
import java.util.List;

import no.niths.domain.school.Event;
import no.niths.infrastructure.interfaces.EventRepository;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class EventRepositoryImpl extends AbstractGenericRepositoryImpl<Event>
		implements EventRepository {

	private QueryGenerator<Event> queryGen;
	private final String COLUMNAME = "tags";
	private Logger logger = LoggerFactory.getLogger(Event.class);

	public EventRepositoryImpl() {
		super(Event.class, new Event());
		queryGen = new QueryGenerator<Event>(Event.class);
	}

	@Override
	public List<Event> getEventsByTag(String tag) {
		return queryGen.whereQuery(tag, COLUMNAME, getSession()
				.getCurrentSession());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getEventsBetweenDates(GregorianCalendar startTime,
			GregorianCalendar endTime) {
		String sql = "FROM " + Event.class.getName() + " e WHERE e.startTime";
		boolean isEndTimeNull = endTime == null;
		if (isEndTimeNull) {
			sql += " >= :startTime";
		} else {
			sql += " BETWEEN :startTime AND :endTime ORDER BY e.startTime asc";
		}
		
		Query query = getSession().getCurrentSession().createQuery(sql);
		query.setTimestamp("startTime", startTime.getTime());
		if(!isEndTimeNull){
			query.setTimestamp("endTime", endTime.getTime());
		}
		

		logger.debug(query.getQueryString());
		return query.list();
	}
}

package no.niths.infrastructure;

import java.util.List;

import no.niths.domain.Event;
import no.niths.infrastructure.interfaces.EventRepositorty;

import org.springframework.stereotype.Repository;

@Repository
public class EventRepositoryImpl extends AbstractGenericRepositoryImpl<Event>
		implements EventRepositorty {

	private QueryGenerator<Event> queryGen;
	private final String COLUMNAME = "tags";

	public EventRepositoryImpl() {
		super(Event.class);
		queryGen = new QueryGenerator<Event>(Event.class);
	}

	@Override
	public List<Event> getEventsByTag(String tag) {
		return queryGen.whereQuery(tag, COLUMNAME, getSession()
				.getCurrentSession());
	}

	@Override
	public void hibernateDelete(long id) {
		Event e = new Event();
		e.setId(id);
		getSession().getCurrentSession().delete(e);
	}

}

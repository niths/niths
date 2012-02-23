package no.niths.infrastructure;

import java.util.List;

import no.niths.common.QueryGenerator;
import no.niths.domain.Event;
import no.niths.infrastructure.interfaces.EventRepositorty;

import org.springframework.stereotype.Repository;

@Repository
public class EventRepositoryImpl extends GenericRepositoryImpl<Event> implements
		EventRepositorty {

	private QueryGenerator queryGen;
	private final String COLUMNAME ="tags";
	
	public EventRepositoryImpl() {
		super(Event.class);
		queryGen= new QueryGenerator();
	}

	@Override
	public List<Event> getEventsByTag(String tag) {
		return queryGen.whereQuery(tag, COLUMNAME , getSession().getCurrentSession());
	}

	
	
}

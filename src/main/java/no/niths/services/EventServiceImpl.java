package no.niths.services;

import java.util.List;

import no.niths.domain.Event;
import no.niths.infrastructure.interfaces.EventRepositorty;
import no.niths.services.interfaces.EventsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EventServiceImpl implements EventsService  {
   
    @Autowired
    private EventRepositorty repo;

    public Long create(Event committeeEvents) {
        return repo.create(committeeEvents);
    }

    public List<Event> getAll(Event event) {
    	List<Event> events = repo.getAll(event);

    	for (int i = 0; i < events.size(); i++) {
    		events.get(i).setCommittee(null);
		}
        return events;
    }
    
    public Event getById(long id) {
    	Event event = repo.getById(id);
    
    	if(event != null){
    		event.getCommittee();
    	}
    	
        return event;
    }
    
    public void update(Event committeeEvents) {
        repo.update(committeeEvents);
        
    }
   
    public boolean delete(long eid) {
        return repo.delete(eid);
    }
}

package no.niths.services;

import java.util.List;

import no.niths.domain.CommitteeEvent;
import no.niths.infrastructure.interfaces.CommitteeEventRepositorty;
import no.niths.services.interfaces.CommitteeEventsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommitteeEventServiceImpl implements CommitteeEventsService  {
   
    @Autowired
    private CommitteeEventRepositorty repo;

    public Long create(CommitteeEvent committeeEvents) {
        return repo.create(committeeEvents);
    }

    public List<CommitteeEvent> getAll(CommitteeEvent event) {
    	List<CommitteeEvent> events = repo.getAll(event);

    	for (int i = 0; i < events.size(); i++) {
    		events.get(i).setCommittee(null);
		}
        return events;
    }
    
    public CommitteeEvent getById(long id) {
    	CommitteeEvent event = repo.getById(id);
    
    	if(event != null){
    		event.getCommittee();
    	}
    	
        return event;
    }
    
    public void update(CommitteeEvent committeeEvents) {
        repo.update(committeeEvents);
        
    }
   
    public boolean delete(long eid) {
        return repo.delete(eid);
    }
}

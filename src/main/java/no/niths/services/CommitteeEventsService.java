package no.niths.services;

import java.util.List;

import no.niths.domain.CommitteeEvent;
import no.niths.infrastructure.interfaces.CommitteeEventsRepositorty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommitteeEventsService  {
   
    @Autowired
    private CommitteeEventsRepositorty repo;

    public Long create(CommitteeEvent committeeEvents) {
        return repo.create(committeeEvents);
    }

//    public List<CommitteeEvent> getAll() {
//        return repo.getAll();
//    }
    
    public List<CommitteeEvent> getAll(CommitteeEvent event) {
        return repo.getAll(event);
    }
    
    public CommitteeEvent getCommitteeEventsById(long ceid) {
        return repo.getById(ceid);
    }
    
    public void update(CommitteeEvent committeeEvents) {
        repo.update(committeeEvents);
        
    }
   
    public void delete(long eid) {
        repo.delete(eid);
    }
}

package no.niths.services;

import java.util.List;

import no.niths.domain.CommitteeEvent;
import no.niths.infrastructure.interfaces.CommitteeEventsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommitteeEventsService  {
   
    @Autowired
    private CommitteeEventsRepository repo;

    public Long create(CommitteeEvent committeeEvents) {
        return repo.create(committeeEvents);
    }

    public List<CommitteeEvent> getAll() {
        return repo.getAll();
    }
    
    public CommitteeEvent getCommitteeEventsById(long ceid) {
        return repo.getCommitteeEventsById(ceid);
    }
    
    public void update(CommitteeEvent committeeEvents) {
        repo.update(committeeEvents);
        
    }
   
    public void delete(long eid) {
        repo.delete(eid);
    }
}

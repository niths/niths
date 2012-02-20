package no.niths.services;

import java.util.List;

import no.niths.domain.CommitteeEvent;
import no.niths.infrastructure.interfaces.CommitteeEventsRepositorty;
import no.niths.services.interfaces.CommitteeEventsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommitteeEventsServiceImpl implements CommitteeEventsService  {
   
    @Autowired
    private CommitteeEventsRepositorty repo;

    public Long create(CommitteeEvent committeeEvents) {
        return repo.create(committeeEvents);
    }

    public List<CommitteeEvent> getAll(CommitteeEvent event) {
        return repo.getAll(event);
    }
    
    public CommitteeEvent getById(long ceid) {
        return repo.getById(ceid);
    }
    
    public void update(CommitteeEvent committeeEvents) {
        repo.update(committeeEvents);
        
    }
   
    public boolean delete(long eid) {
        return repo.delete(eid);
    }
}

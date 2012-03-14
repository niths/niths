package no.niths.services;

import java.util.List;

import no.niths.domain.APIEvent;
import no.niths.infrastructure.interfaces.APIEventRepository;
import no.niths.services.interfaces.APIEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * Service class for APIEvents
 * To persist the event,  annotate any public method with @ApiEvent(title = "title).
 * 
 * The method must be public and take one parameter.
 * 
 * How to use:
 * @ApiEvent(title="Something happened")
 * public void anyMethod(Object obj){}
 * 
 *
 */
@Service
@Transactional
public class APIEventServiceImpl implements APIEventService{

    @Autowired
    private APIEventRepository repo;

    public Long create(APIEvent event) {
        return repo.create(event);
    }

    public APIEvent getById(long id) {
    	return repo.getById(id);
   }

    public List<APIEvent> getAll(APIEvent event) {
    	return repo.getAll(event);
    }

    public void update(APIEvent event) {
        repo.update(event);
    }

    public boolean delete(long id) {
        return repo.delete(id);
    }

	@Override
	public void hibernateDelete(long id) {
		repo.hibernateDelete(id);
	}
}
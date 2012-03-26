package no.niths.services;

import no.niths.domain.APIEvent;
import no.niths.infrastructure.interfaces.APIEventRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.interfaces.APIEventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for APIEvents To persist the event, annotate any public method
 * with @ApiEvent(title = "title).
 * 
 * The method must be public and take one parameter.
 * 
 * How to use:
 * 
 * @ApiEvent(title="Something happened") public void anyMethod(Object obj){}
 * 
 * 
 */
@Service
public class APIEventServiceImpl extends AbstractGenericService<APIEvent> implements APIEventService {
	
	@Autowired
	private APIEventRepository repo;

	@Override
	public GenericRepository<APIEvent> getRepository() {
		return repo;
	}
}
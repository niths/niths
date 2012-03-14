package no.niths.infrastructure;

import no.niths.domain.APIEvent;
import no.niths.infrastructure.interfaces.APIEventRepository;

import org.springframework.stereotype.Repository;
/**
 * Repository class for APIEvents
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
@Repository
public class APIEventRepositoryImpl extends GenericRepositoryImpl<APIEvent>
		implements APIEventRepository {

	public APIEventRepositoryImpl() {
		super(APIEvent.class);
	}

	@Override
	public void hibernateDelete(long id) {
		APIEvent s = new APIEvent();
		s.setId(id);
		getSession().getCurrentSession().delete(s);
		
	}
}
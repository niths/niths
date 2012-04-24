package no.niths.infrastructure.misc;

import no.niths.domain.APIEvent;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
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
public class APIEventRepositoryImpl extends AbstractGenericRepositoryImpl<APIEvent>
		implements APIEventRepository {

	public APIEventRepositoryImpl() {
		super(APIEvent.class, new APIEvent());
	}
}
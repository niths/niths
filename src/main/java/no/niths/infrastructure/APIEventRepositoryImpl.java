package no.niths.infrastructure;

import no.niths.domain.APIEvent;
import no.niths.infrastructure.interfaces.APIEventRepository;

import org.springframework.stereotype.Repository;

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
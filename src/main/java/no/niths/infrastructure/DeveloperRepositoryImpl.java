package no.niths.infrastructure;

import no.niths.domain.Developer;
import no.niths.infrastructure.interfaces.DeveloperRepository;

import org.springframework.stereotype.Repository;

@Repository
public class DeveloperRepositoryImpl extends GenericRepositoryImpl<Developer>
		implements DeveloperRepository {

	public DeveloperRepositoryImpl() {
		super(Developer.class);
	}

	@Override
	public void hibernateDelete(long id) {
		Developer s = new Developer();
		s.setId(id);
		getSession().getCurrentSession().delete(s);
	}
}
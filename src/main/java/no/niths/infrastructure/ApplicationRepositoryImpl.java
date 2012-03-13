package no.niths.infrastructure;

import no.niths.domain.Application;
import no.niths.domain.Subject;
import no.niths.infrastructure.interfaces.ApplicationRepository;
import no.niths.infrastructure.interfaces.SubjectRepository;

import org.springframework.stereotype.Repository;

@Repository
public class ApplicationRepositoryImpl extends GenericRepositoryImpl<Application>
		implements ApplicationRepository {

	public ApplicationRepositoryImpl() {
		super(Application.class);
	}

	@Override
	public void hibernateDelete(long id) {
		Application s = new Application();
		s.setId(id);
		getSession().getCurrentSession().delete(s);
	}
}
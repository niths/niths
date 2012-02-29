package no.niths.infrastructure;

import no.niths.domain.Subject;
import no.niths.infrastructure.interfaces.SubjectRepository;

import org.springframework.stereotype.Repository;

@Repository
public class SubjectRepositoryImpl extends GenericRepositoryImpl<Subject>
		implements SubjectRepository {

	public SubjectRepositoryImpl() {
		super(Subject.class);
	}

	@Override
	public void hibernateDelete(long id) {
		Subject s = new Subject();
		s.setId(id);
		getSession().getCurrentSession().delete(s);
		
	}
}
package no.niths.infrastructure;

import no.niths.domain.Subject;
import no.niths.infrastructure.interfaces.SubjectRepository;

import org.springframework.stereotype.Repository;

@Repository
public class SubjectRepositoryImpl extends AbstractGenericRepositoryImpl<Subject>
		implements SubjectRepository {

	public SubjectRepositoryImpl() {
		super(Subject.class, new Subject());
	}

}
package no.niths.infrastructure;

import no.niths.domain.Subject;
import no.niths.infrastructure.interfaces.SubjectRepository;

import org.springframework.stereotype.Repository;

@Repository
public class TopicsRepositoryImpl extends GenericRepositoryImpl<Subject>
		implements SubjectRepository {

	public TopicsRepositoryImpl() {
		super(Subject.class);
	}

	
}
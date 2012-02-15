package no.niths.infrastructure;

import no.niths.domain.Topic;
import no.niths.infrastructure.interfaces.TopicsRepository;

import org.springframework.stereotype.Repository;

@Repository
public class TopicsRepositoryImpl extends GenericRepositoryImpl<Topic>
		implements TopicsRepository {

	public TopicsRepositoryImpl() {
		super(Topic.class);
	}

	
}
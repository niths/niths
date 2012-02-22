package no.niths.infrastructure;

import no.niths.domain.CommitteeEvent;
import no.niths.infrastructure.interfaces.CommitteeEventRepositorty;

import org.springframework.stereotype.Repository;

@Repository
public class CommitteeEventsRepositoryImpl extends
		GenericRepositoryImpl<CommitteeEvent> implements
		CommitteeEventRepositorty {

	public CommitteeEventsRepositoryImpl() {
		super(CommitteeEvent.class);
	}

}

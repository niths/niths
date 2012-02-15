package no.niths.infrastructure;

import no.niths.domain.CommitteeEvent;
import no.niths.infrastructure.interfaces.CommitteeEventsRepositorty;

import org.springframework.stereotype.Repository;

@Repository
public class CommitteeEventsRepositoryImpl extends
		GenericRepositoryImpl<CommitteeEvent> implements
		CommitteeEventsRepositorty<CommitteeEvent> {

	public CommitteeEventsRepositoryImpl() {
		super(CommitteeEvent.class);
	}

}

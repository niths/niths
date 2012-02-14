package no.niths.infrastructure;

import org.springframework.stereotype.Repository;

import no.niths.domain.CommitteeEvent;

@Repository
public class EventGeneric extends GenericRepositoryImpl<CommitteeEvent>{
	public EventGeneric() {
		super(CommitteeEvent.class);
	}
}

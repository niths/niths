package no.niths.infrastructure.interfaces;

import java.util.List;

import no.niths.domain.CommitteeEvent;

public interface CommitteeEventsRepository {

	Long create(CommitteeEvent committeeEvents);
	
	//Read
	List<CommitteeEvent> getAll();
	
	CommitteeEvent getCommitteeEventsById(long ceid);
	
	void update(CommitteeEvent committeeEvents);
	
	void delete(long eid);
}

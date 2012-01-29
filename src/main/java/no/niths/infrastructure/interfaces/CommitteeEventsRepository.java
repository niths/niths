package no.niths.infrastructure.interfaces;

import java.util.List;

import no.niths.domain.CommitteeEvents;

public interface CommitteeEventsRepository {

	Long create(CommitteeEvents committeeEvents);
	
	//Read
	List<CommitteeEvents> getAll();
	
	CommitteeEvents getCommitteeEventsById(long ceid);
	
	void update(CommitteeEvents committeeEvents);
	
	CommitteeEvents delete(CommitteeEvents committeeEvents);
}

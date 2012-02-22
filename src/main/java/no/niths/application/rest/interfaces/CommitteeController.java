package no.niths.application.rest.interfaces;

import no.niths.domain.Committee;

public interface CommitteeController extends GenericRESTController<Committee> {

	public void addLeader(Long committeeId,
			Long studentId);
	
	public void removeLeader(Long committeeId, Long studentId);
	
	public void notUniqueObject();
}

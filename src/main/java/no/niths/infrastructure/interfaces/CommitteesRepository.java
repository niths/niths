package no.niths.infrastructure.interfaces;

import java.util.List;

import no.niths.domain.Committee;

public interface CommitteesRepository {

	Long create(Committee committee);
	
	//Read
	List<Committee> getAllCommittees();
	List<Committee> getAllCommittees(Committee committee);
	
	Committee getCommitteeById(long cid);
	
	Committee getCommitteeByName(String name);
	
	void update(Committee committee);
	
	boolean delete(long cid);

}

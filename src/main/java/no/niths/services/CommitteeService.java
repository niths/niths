package no.niths.services;

import java.util.List;

import no.niths.domain.Committee;
import no.niths.infrastructure.interfaces.CommitteesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommitteeService  {

	@Autowired
	private CommitteesRepository repo;

	/**
	 * 
	 * @param committee
	 * @return
	 */
	public Long create(Committee committee) {
		return repo.create(committee);
	}

	/**
	 * 
	 * @return
	 */
	public List<Committee> getAll() {
		return repo.getAll();
	}

	
	/**
	 * 
	 * @param cid
	 * @return
	 */
	public Committee getCommitteeById(long cid) {
		return repo.getCommitteeById(cid);
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public Committee getCommitteeByName(String name) {
		return repo.getCommitteeByName(name);
	}

	/**
	 * 
	 * @param committee
	 */
	public void update(Committee committee) {
		repo.update(committee);
	}
	
	/**
	 * 
	 * @param committee
	 * @return
	 */
	public Committee delete(Committee committee) {
		return repo.delete(committee);
	}
	
	public Committee getCommitteeByIdWithStudents(long cid){
		return repo.getCommitteeByIdWithStudents(cid);
	}
}

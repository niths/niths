package no.niths.services;

import java.util.ArrayList;
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
	 * <
	 * @return
	 */
	public List<Committee> getAll() {
		List<Committee> temp = repo.getAllCommittees();
	
		for (int i = 0; i < temp.size(); i++) {
			temp.get(i).setEvents(null);
		}
		return temp;
	}

	/**
	 * <
	 * @return
	 */
	public List<Committee> getAll(Committee committee) {
		List<Committee> temp = repo.getAllCommittees(committee);
	
		for (int i = 0; i < temp.size(); i++) {
			temp.get(i).setEvents(null);
		}
		return temp;
	}
	
	
	/**
	 * 
	 * @param cid
	 * @return
	 */
	public Committee getCommitteeById(long cid) {
		Committee c = repo.getCommitteeById(cid);
		
		if(c != null){
			if(c.getEvents().size() < 1){
				c.setEvents(null);
			}
		}
		return c;
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
	 */
	public void delete(long id) {
		repo.delete(id);
	}
	

}

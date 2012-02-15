package no.niths.services;

import java.util.List;

import no.niths.domain.Committee;
import no.niths.infrastructure.interfaces.CommitteeRepositorty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommitteeService extends GenericService<Committee> {

	@Autowired
	private CommitteeRepositorty repo;

	/**
	 * 
	 * @param committee
	 * @return
	 */
	public void create(Committee committee) {
		repo.create(committee);
	}

	/**
	 * <
	 * @return
	 */
	public List<Committee> getAll(Committee committee) {
		List<Committee> temp = repo.getAll(committee);
	
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
	public Committee getById(long cid) {
		Committee c = repo.getById(cid);
		
		if(c != null){
			if(c.getEvents().size() < 1){
				c.setEvents(null);
			}
		}
		return c;
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

package no.niths.services;

import java.util.List;

import no.niths.domain.Committee;
import no.niths.domain.Student;
import no.niths.infrastructure.interfaces.CommitteeRepositorty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommitteeService  {

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
			temp.get(i).setLeaders(null);
			//If we want leaders to show aswell
//			List<Student> leaders = temp.get(i).getLeaders();
//			for(int j = 0; j < leaders.size(); j++){
//				leaders.get(j).setCourses(null);			
//				leaders.get(j).setCommittees(null);			
//			}
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
			List<Student> leaders = c.getLeaders();
			for (int i = 0; i < leaders.size(); i++){
				leaders.get(i).setCommittees(null);
				leaders.get(i).setCourses(null);
			}
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
	public boolean delete(long id) {
		return repo.delete(id);
	}
	

}

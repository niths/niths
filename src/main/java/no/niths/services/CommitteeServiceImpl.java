package no.niths.services;

import java.util.List;

import no.niths.domain.Committee;
import no.niths.infrastructure.interfaces.CommitteeRepositorty;
import no.niths.services.interfaces.CommitteeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommitteeServiceImpl implements CommitteeService {

	@Autowired
	private CommitteeRepositorty repo;

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
	 * 
	 * @return
	 */
	public List<Committee> getAll(Committee committee) {
		return repo.getAll(committee);
	}

	/**
	 * 
	 * @param cid
	 * @return
	 */
	public Committee getById(long cid) {
		Committee c = repo.getById(cid);

		if (c != null) {
			c.getLeaders().size();
			c.getEvents().size();
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

	@Override
	public void hibernateDelete(long id) {
		repo.hibernateDelete(id);

	}

}

package no.niths.infrastructure;

import java.util.List;

import no.niths.domain.Committee;
import no.niths.infrastructure.interfaces.CommitteesRepository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CommitteeRepositoryImpl implements CommitteesRepository {

	@Autowired
	private SessionFactory session;
	
	
	@Transactional(readOnly = false)
	public Long create(Committee domain) {
		return (Long) session.getCurrentSession().save(domain);
	}

	@SuppressWarnings("unchecked")
	public List<Committee> getAllCommittees() {
		return session.getCurrentSession().createQuery("from " + Committee.class.getName())
				.list();
	}

	public Committee getCommitteeById(long cid) {
		return (Committee) session.getCurrentSession().get(Committee.class, cid);
	}

	@Transactional(readOnly = false)
	public void update(Committee domain) {
		session.getCurrentSession().update(domain);
	}

	@Transactional(readOnly = false)
	public void delete(Committee committee) {
	
		session.getCurrentSession().delete(Committee.class.getSimpleName(), committee);
	
	}

	@Override
	public Committee getCommitteeByName(String name) {
		String sql ="from " + Committee.class.getName() + " c where c.name=:name";
		return (Committee) session.getCurrentSession().createQuery(sql).setString("name",name).uniqueResult();
	}

}

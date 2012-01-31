package no.niths.infrastructure;

import java.util.List;

import no.niths.domain.CommitteeEvents;
import no.niths.infrastructure.interfaces.CommitteeEventsRepository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CommitteeEventsRepositoryImpl implements CommitteeEventsRepository {

	@Autowired
	private SessionFactory session;

	@Transactional(readOnly = false)
	public Long create(CommitteeEvents domain) {
		return (Long) session.getCurrentSession().save(domain);
	}

	@SuppressWarnings("unchecked")
	public List<CommitteeEvents> getAll() {
		return session.getCurrentSession().createQuery("from " + CommitteeEvents.class.getName())
				.list();
	}

	public CommitteeEvents getCommitteeEventsById(long cid) {
		return (CommitteeEvents) session.getCurrentSession().get(CommitteeEvents.class, cid);
	}

	@Transactional(readOnly = false)
	public void update(CommitteeEvents domain) {
		session.getCurrentSession().update(domain);
	}

	@Transactional(readOnly = false)
	public CommitteeEvents delete(CommitteeEvents domain) {
		session.getCurrentSession().delete(domain);
		return domain;
	}

	
}

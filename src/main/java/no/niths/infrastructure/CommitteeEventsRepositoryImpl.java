package no.niths.infrastructure;

import java.util.List;

import no.niths.domain.Committee;
import no.niths.domain.CommitteeEvent;
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
	public Long create(CommitteeEvent domain) {
		return (Long) session.getCurrentSession().save(domain);
	}

	@SuppressWarnings("unchecked")
	public List<CommitteeEvent> getAll() {
		return session.getCurrentSession().createQuery("from " + CommitteeEvent.class.getName())
				.list();
	}

	public CommitteeEvent getCommitteeEventsById(long cid) {
		return (CommitteeEvent) session.getCurrentSession().get(CommitteeEvent.class, cid);
	}

	@Transactional(readOnly = false)
	public void update(CommitteeEvent domain) {
		session.getCurrentSession().update(domain);
	}

	@Transactional(readOnly = false)
	public void delete(long eid) {
	    session.getCurrentSession().clear();
        session.getCurrentSession().delete(new CommitteeEvent(eid,"","",null));
	}
}

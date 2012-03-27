package no.niths.infrastructure;

import java.util.List;

import no.niths.domain.Domain;
import no.niths.infrastructure.interfaces.GenericRepository;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Abstract generic repository class that generates
 * all the basic CRUD actions for repository classes to 
 * extends 
 * @param <T>
 */
public abstract class AbstractGenericRepositoryImpl<T extends Domain>
		implements GenericRepository<T> {

	private Class<T> persistentClass;
	private Domain domain;

	/**
	 * Constructor takes the persistence class and the persistence domain
	 * @param persistentClass
	 * @param domain
	 */
	public AbstractGenericRepositoryImpl(Class<T> persistentClass, Domain domain) {
		this.persistentClass = persistentClass;
		this.domain = domain;
	}

	@Autowired
	private SessionFactory session;

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	public Long create(T domain) {
		return (Long) session.getCurrentSession().save(domain);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll(T domain) {
		Criteria criteria = session.getCurrentSession()
				.createCriteria(persistentClass);
		if (domain != null) {
			criteria.add(Example.create(domain).enableLike(MatchMode.ANYWHERE));
		}
		return criteria.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T getById(long id) {
		return (T) session.getCurrentSession().get(persistentClass, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	public void update(T domain) {
		session.getCurrentSession().update(domain);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional(readOnly = false)
	public boolean delete(long id) {
		Query query = session.getCurrentSession()
				.createQuery(
						"delete " + persistentClass.getSimpleName()
								+ " where id = :id");
		query.setParameter("id", id);
		return (1 == query.executeUpdate());
	}

	/**
	 * Returns a instance of the persistence class
	 * 
	 * @return
	 */
	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	/**
	 * Returns the given session
	 * @return
	 */
	public SessionFactory getSession() {
		return session;
	}

	/**
	 * {@inheritDoc}
	 */
	public void hibernateDelete(long id) {
		domain.setId(id);
		getSession().getCurrentSession().delete(domain);
	}
}

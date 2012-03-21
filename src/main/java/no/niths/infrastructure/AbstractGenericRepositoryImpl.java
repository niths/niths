package no.niths.infrastructure;

import java.io.Serializable;
import java.util.List;

import no.niths.infrastructure.interfaces.GenericRepository;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * 
 * @param <T>
 */
public abstract class AbstractGenericRepositoryImpl<T extends Serializable>
		implements GenericRepository<T> {

	private Class<T> persistentClass;

	public AbstractGenericRepositoryImpl(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	@Autowired
	private SessionFactory session;

	@Transactional(readOnly = false)
	public Long create(T domain) {
		return (Long) session.getCurrentSession().save(domain);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll(T domain) {
		if (domain == null) {
			return session.getCurrentSession()
					.createQuery("from " + persistentClass.getSimpleName())
					.list();
		} else {
			return session.getCurrentSession().createCriteria(persistentClass)
					.add(Example.create(domain)).list();
		}
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
	 * 
	 */
	public abstract void hibernateDelete(long id);
}

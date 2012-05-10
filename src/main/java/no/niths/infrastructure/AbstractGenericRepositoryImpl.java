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
 * Abstract generic repository class that generates all the basic CRUD actions
 * for repository classes.
 * <p>
 * How to use:
 * <p>
 * First create an interface that defines the methods. Naming is important for
 * the code to be easy to read, so call it YourDomainRepository.
 * <p>
 * If you keep the interface empty, it will give you the basic CRUD methods:
 * create, update, delete, getById and getAll
 * <p>
 * Then create a new class that implements the interface and, extends this class
 * like this (Remember naming convention):
 * <p>
 * 
 * <pre>
 * {@code
 * Repository
 * public class YourDomainRepositoryImpl extends AbstractGenericRepositoryImpl<YourDomain>
 *                         implements YourDomainRepository{
 * 
 * public YourDomainRepositoryImpl() {
 *     super(YourDomain.class, new YourDomain());
 * }
 * 
 * }
 * }
 * 
 * </pre>
 * 
 * @param <T>
 */
public abstract class AbstractGenericRepositoryImpl<T extends Domain>
        implements GenericRepository<T> {

    private Class<T> persistentClass;
    private Domain domain;

    /**
     * Constructor takes the persistence class and the persistence domain
     * <p>
     * 
     * @param persistentClass
     *            the domain class
     * @param domain
     *            an empty object of your domain type
     */
    public AbstractGenericRepositoryImpl(Class<T> persistentClass, Domain domain) {
        this.persistentClass = persistentClass;
        this.domain = domain;
    }

    @Autowired
    private SessionFactory session;

    /**
     * Persist a provided domain
     * <p>
     * 
     * @param domain
     *            the object you want to persist
     * @return id of the domain
     */
    @Transactional(readOnly = false)
    public Long create(T domain) {
        return (Long) session.getCurrentSession().save(domain);
    }

    /**
     * Find and returns all objects which has values equal to the object sent as
     * parameter.
     * 
     * @param domain
     *            - The object that has the values to search for
     * @return List of objects found
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll(T domain) {
        return buildCriteria(domain, MatchMode.ANYWHERE).list();
    }

    /**
     * Find and returns all objects which has values equal to the object sent as
     * parameter.
     * <p>
     * Supports pagination
     * <p>
     * 
     * @param domain
     *            the object that has the values to search for
     * @param firstResult
     *            the first result
     * @param maxResults
     *            number of results in the returning list
     * @return List of objects found
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll(T domain, int firstResult, int maxResults) {
        return (List<T>) buildCriteria(domain, MatchMode.ANYWHERE)
                .setFirstResult(firstResult).setMaxResults(maxResults).list();
    }

    /**
     * Helper method for creating criteria
     * @param domain
     * @param matchMode
     * @return
     */
    private Criteria buildCriteria(T domain, MatchMode matchMode) {
        Criteria criteria = session.getCurrentSession().createCriteria(
                persistentClass);
        if (domain != null) {
            criteria.add(Example.create(domain).enableLike(matchMode));
        }
        return criteria;
    }

    /**
     * Returns a domain on a given id
     * @param id
     * @return the domain with matching ID, or null if not found
     */
    @SuppressWarnings("unchecked")
    public T getById(long id) {
        return (T) session.getCurrentSession().get(persistentClass, id);
    }

    /**
     * Updates a existing domain
     * <p>
     * 
     * @param domain
     *            the object
     */
    @Transactional(readOnly = false)
    public void update(T domain) {
        session.getCurrentSession().update(domain);
    }

    /**
     * Deletes a domain on a given id
     * 
     * @param id
     * @return true if update succeeded, false otherwise
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
     * Deletes a domain on a given id and deletes relationships.
     * 
     * @param id
     *            the id of the object to delete
     */
    public void hibernateDelete(long id) {
        domain.setId(id);
        getSession().getCurrentSession().delete(domain);
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
     * 
     * @return session
     */
    public SessionFactory getSession() {
        return session;
    }
}

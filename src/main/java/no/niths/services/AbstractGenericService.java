package no.niths.services;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import no.niths.application.rest.exception.BadRequestException;
import no.niths.application.rest.exception.ObjectInCollectionException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.helper.Error;
import no.niths.common.helpers.LazyFixer;
import no.niths.common.helpers.MessageProvider;
import no.niths.common.helpers.ValidationHelper;
import no.niths.domain.Domain;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.interfaces.GenericService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Abstract class to use for services. Extending this class will your service
 * basic CRUD actions
 * <p>
 * How to use:
 * <p>
 * Create an interface called YourDomainService
 * <p>
 * Then create an implementation class that overrides getRepository() like this:
 * 
 * <pre>
 * {@code
 * public class YourDomainServiceImpl extends AbstractGenericService<YourDomain> 
 *                             implements YourDomainService{
 *         Autowired
 *      private YourDomainRepository repository;
 * 
 *         Override
 *         public GenericRepository<YourDomain> getRepository() {
 *         return repository;
 *         }
 * 
 * }
 * 
 * }
 * 
 * <pre>
 * 
 * @param <T>
 */
@Transactional
public abstract class AbstractGenericService<T extends Domain> implements
        GenericService<T> {

    private Logger logger = LoggerFactory
            .getLogger(AbstractGenericService.class);
    private CustomBeanUtilsBean mergeBean = new CustomBeanUtilsBean();

    private Validator validator =
            Validation.buildDefaultValidatorFactory().getValidator();

    private LazyFixer<T> lazyFixer = new LazyFixer<T>();

    /**
     * Calls on repository to persist the domain
     * 
     * @param domain
     *            the object to persist
     * @return id of the object created
     * 
     */
    @Override
    public Long create(T domain) {
        validateFields(domain);
        return getRepository().create(domain);
    }
    
    private void validateFields(T domain) {
        Set<ConstraintViolation<T>> violations = validator.validate(domain);
        for (ConstraintViolation<T> violation : violations) {
            throw new BadRequestException(String.format(
                    "Invalid values - %s",
                    violation.getMessage()));
        }
    }

    /**
     * Calls on repository to get all objects of a certain type
     * 
     * @param domain
     *            empty object to return all, or with some attributes to search
     *            for
     * @return list of objects
     */
    @Override
    public List<T> getAll(T domain) {
        List<T> list = getRepository().getAll(domain);
        return list;
    }

    @Override
    public List<T> getAll(T domain, int firstResult, int maxResults) {
        return getRepository().getAll(domain, firstResult, maxResults);
    }

    /**
     * Calls on repository to get a certain object
     * 
     * @param id
     *            of the object
     * @return object with matching ID, or null if no object found
     */
    @Override
    public T getById(long id) {
        T t = getRepository().getById(id);
        ArrayList<T> ts = new ArrayList<T>();
        ts.add(t);
        lazyFixer.fetchChildren(ts);
        return ts.get(0);
    }

    /**
     * Calls on repository to update a domain
     * <p>
     * Will override the current object in DB
     * <p>
     * 
     * @param domain
     *            , the domain to update
     */
    @Override
    public void update(T domain) {
        System.err.println(domain.toString() + "hello");
        getRepository().update(domain);
    }

    /**
     * Updates an object
     * <p>
     * Difference from update() is that only the attributes with value will be
     * updated. All null attributes will not affect the object in DB
     * <p>
     * 
     * @param domain
     *            the domain to update
     */
    public void mergeUpdate(T domain) {
        ValidationHelper.isObjectNull(domain.getId());
        T domaineToUpdate = getRepository().getById(domain.getId());
        ValidationHelper.isObjectNull(domaineToUpdate);

        try {
            mergeBean.copyProperties(domaineToUpdate, domain);
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error("error", e);
            e.printStackTrace();
        }
    }

    /**
     * Deletes the object in DB with the given id
     * 
     * @param id
     *            the id of the object
     * @return true if update succeeds, false otherwise
     */
    @Override
    public boolean delete(long id) {
        return getRepository().delete(id);
    }

    /**
     * Deletes an object in DB with the given id. All relationships will also be
     * deleted
     * <p>
     * 
     * @param id
     *            of the object to delete
     */
    @Override
    public void hibernateDelete(long id) {
        getRepository().hibernateDelete(id);
    }

    /**
     * Override this method to return an instance of your repository
     * <p>
     * 
     * @return an instance of your repository
     */
    public abstract GenericRepository<T> getRepository();

    
    /**
     * Helper method for checking if the the list element is a instance of
     * Domain and then we can cast the list element to a Domain for using the
     * getId() method
     * 
     * @throws ObjectInCollectionException
     *             () if the object is found
     * @param list
     * @param id
     */
    @SuppressWarnings("rawtypes")
    public void checkIfObjectIsInCollection(List list, long id,Class clazz) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof Domain) {
                Domain d = (Domain) list.get(i);
                if (d.getId() == id) {
                    throw new ObjectInCollectionException(
                            MessageProvider.buildErrorMsg(clazz,
                                    Error.OBJECT_IN_COLLECTION));
                }
            }
        }
    }
    
    @SuppressWarnings("rawtypes")
    public T validate(T domain, Class clazz) {
        ValidationHelper.isObjectNull(domain, clazz);
        return domain;
    }

    @SuppressWarnings("rawtypes")
    public void checkIfIsRemoved(boolean isRemoved, Class clazz) {
        if (!isRemoved) {
            String msg = MessageProvider.buildErrorMsg(clazz, Error.NOT_FOUND);
            logger.debug(msg);
            throw new ObjectNotFoundException(msg);
        }
    }
    
    @SuppressWarnings("rawtypes")
    public void checkIfObjectExists(Domain domain, Long id,Class clazz) {
        if (domain != null && domain.getId() == id) {
            String msg = MessageProvider.buildErrorMsg(clazz, Error.OBJECT_IN_COLLECTION);
            logger.debug(msg);
            throw new ObjectInCollectionException(msg);
        }
    }
}

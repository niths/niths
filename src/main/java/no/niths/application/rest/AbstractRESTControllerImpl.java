package no.niths.application.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.constants.SecurityConstants;
import no.niths.common.helpers.LazyFixer;
import no.niths.common.helpers.ValidationHelper;
import no.niths.services.interfaces.GenericService;

import org.hibernate.TransientObjectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Abstract class that holds logic for CRUD operations on a given domain type
 * 
 * To add new controllers, create an interface that extends
 * GenericRESTController<your_domain>, then create a class that extends
 * AbstractRESTControllerImpl<your_domain> and implements YourInterface
 * 
 * <pre>
 * {@code
 * @Autowire your service and create a new list in 
 * @package no.niths.application.rest.list and 
 * @Override the two methods:
 * public GenericService<Your_domain> getService() public
 *         ListAdapter<Your_domain> getList() to return your service and your list
 * }
 * </pre>
 * 
 * Add extra methods by defining them in the interface and override them in the
 * implementation class Call super.methodName(parameter) to execute CRUD methods
 * 
 * @param <T>
 *            The domain type
 * 
 */
public abstract class AbstractRESTControllerImpl<T> extends RESTExceptionHandler implements
        GenericRESTController<T> {
 
    private static final Logger logger = LoggerFactory
            .getLogger(AbstractRESTControllerImpl.class);

    private LazyFixer<T> nullifier = new LazyFixer<T>();

    /**
     * Persists the domain
     * 
     * @param domain
     *            the domain to persist
     * 
     *            <pre>
     * {@code
     * @PreAuthorize(SecurityConstants.ONLY_ADMIN)//Optional security public
     * void create(@RequestBody Your_domain domain){
     *         super.create(domain); 
     * }
     * 
     * URL = {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}/YOUR_DOMAINs
     * method = RequestMethod.POST
     * value = HttpStatus.CREATED
     * reason = "Created"
     * </pre>
     */
    @Override
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Created")
    @PreAuthorize(SecurityConstants.ONLY_ADMIN)
    public void create(@RequestBody T domain, HttpServletResponse res) {
            logger.debug(domain +"");
            res.addHeader(
                    "location",
                    String.valueOf(getService().create(domain)));
    }
    /**
     * Returns the domain object with the given id
     * 
     * @param id
     *            the id of the domain object
     * @return the domain object
     * @throws ObjectNotFoundException
     *             when object is not found
     * 
     *             Usage in your own class:
     * 
     *             <pre>
     * {@code
     * @Override
     * public You_Domain getById(@PathVariable Long id) {
     *         return super.getById(id);
     * }
     * URL = {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}/YOUR_DOMAINs/{id}
     * value = "{id}", 
     * method = RequestMethod.GET, 
     * headers = RESTConstants.ACCEPT_HEADER
     * </pre>
     *
     */
    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
    @ResponseBody
    public T getById(@PathVariable Long id) {
    	T domain = getService().getById(id);
    	ValidationHelper.isObjectNull(domain);
    	nullifier.clearSubRelations(domain);
    	logger.debug(domain.toString());
    	return domain;
    }

    /**
     * 
     * Returns an array list with all domain objects of the type
     * 
     * @param domain
     *            will search the DB for instances with the same attributes, if
     *            null, all will be returned
     * @return List of all domain objects
     * 
     *         Usage in your own class:
     * 
     *         <pre>
     * {@code
     * @Override
     * @PreAuthorize(SecurityConstants.ONLY_ADMIN) //Optional security
     * public ArrayList<Your_Domain> getAll(Your_Domain domain) {
     *         ArrayList<Your_Domain> all = super.getAll(domain);
     * 		return roles;
     * }
     * URL = {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}/YOUR_DOMAINs
     * method = RequestMethod.GET
     *  headers = RESTConstants.ACCEPT_HEADER
     * </pre>
     * 
     */
    @Override
    @RequestMapping(method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
    @ResponseBody
    public ArrayList<T> getAll(T domain) {
        renewList(getService().getAll(domain));
        return getList();
    }

    /**
     * 
     * Returns an array list with all exams like getAll(domain), 
     * but also supports pagination
     * @param domain object with attributes to search for
     * @param firstResult the first result in the result set
     * @param maxResults the number of result to return
     * <pre>
     * URL = {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}/YOUR_DOMAINs/paginated/{firstResult}/{maxResults}
     * method = RequestMethod.GET
     * headers = RESTConstants.ACCEPT_HEADER
     * </pre>
     */
    @Override
    @RequestMapping(value = "paginated/{firstResult}/{maxResults}", method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
    @ResponseBody
    public ArrayList<T> getAll(T domain,@PathVariable int firstResult, @PathVariable int maxResults) {
        renewList(getService().getAll(domain, firstResult, maxResults));
        return getList();
    }

    /**
     * Update the domain object
     * 
     * @param domain
     *            the domain
     * @throws ObjectNotFoundException
     *             when object is not found
     * 
     *             Usage in your own class:
     * 
     *             <pre>
     * {@code
     * @Override
     * @PreAuthorize(SecurityConstants.ONLY_ADMIN)//Optional security public
     * void update(@RequestBody Your_domain domain){
     *         super.update(domain); 
     * }
     * 
     * URL = {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}/YOUR_DOMAINs
     * method = RequestMethod.PUT
     * value = HttpStatus.OK, 
     * reason = "Update OK"
     * </pre>
     * 
     */
    @Override
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Update OK")
    @PreAuthorize(SecurityConstants.ONLY_ADMIN)
    public void update(@RequestBody T domain) {
        logger.debug("Update");
        logger.debug(domain + "");

        try {
            getService().mergeUpdate(domain);
        } catch (TransientObjectException e) {
            throw new ObjectNotFoundException(e.getMessage().toString());
        }
    }

    /**
     * {@inheritDoc}
     */
    public void renewList(List<T> list) {
        getList().clear();
        getList().addAll(list);
        getList().setData(getList()); // Used for XML marshaling
        ValidationHelper.isListEmpty(getList());
        nullifier.clearRelations(getList());
    }

    /**
     * Deletes the domain object with the given id
     * 
     * @param id
     *            the if of the domain object to be deleted
     * 
     *            Usage in your own class:
     * 
     *            <pre>
     * {@code
     * @Override
     * @PreAuthorize(SecurityConstants.ONLY_ADMIN)//Optional security public
     * void hibernateDelete(@PathVariable long id){
     *         super.hibernateDelete(id); 
     * }
     * 
     * URL = {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}/YOUR_DOMAINs/{id}
     * @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
     * @ResponseStatus(value = HttpStatus.OK, reason = "Deleted")
     * </pre>
     * 
     * 
     */
    @Override
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK, reason = "Deleted")
    @PreAuthorize(SecurityConstants.ONLY_ADMIN)
    public void delete(@PathVariable long id) {
        try {
            getService().hibernateDelete(id);
        } catch (HibernateOptimisticLockingFailureException e) {
            throw new ObjectNotFoundException("Could not find the object");
        }
    }

    /**
     * Represents the service
     * 
     * Must override in own implementation
     * 
     * <pre>
     * {@code
     * @Override
     * public GenericService<Your_domain> getService() {
     *         return yourService;
     * }
     * </pre>
     * 
     * @return the service of a given type
     */
    public abstract GenericService<T> getService();

    /**
     * Adapter for XML presentation of a list
     * 
     * Must override in own implementation
     * 
     * <pre>
     * {@code
     * @Override
     * public ListAdapter<Your_Domain> getList() {
     *         return your_domainList;
     * }
     * </pre>
     * 
     * @return Array list of a given type
     */
    public abstract ListAdapter<T> getList();

}
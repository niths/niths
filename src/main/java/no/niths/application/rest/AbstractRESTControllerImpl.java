package no.niths.application.rest;

import java.util.ArrayList;
import java.util.List;

import no.niths.application.rest.exception.ExpiredTokenException;
import no.niths.application.rest.exception.IdentifierNullException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.exception.UnvalidEmailException;
import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.ValidationHelper;
import no.niths.services.interfaces.GenericService;

import org.hibernate.TransientObjectException;
import org.hibernate.exception.ConstraintViolationException;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Abstract class that holds logic for CRUD operations on a given domain type
 * 
 * @param <T>
 *            The type parameter
 */
public abstract class AbstractRESTControllerImpl<T> implements
		GenericRESTController<T> {

	private static final Logger logger = LoggerFactory
			.getLogger(AbstractRESTControllerImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED, reason = "Created")
	public void create(@RequestBody T domain) {
		getService().create(domain);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public T getById(@PathVariable Long id) {
		logger.info("method used");
		T domain = getService().getById(id);
		ValidationHelper.isObjectNull(domain);
		return domain;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public ArrayList<T> getAll(T domain) {
		renewList(getService().getAll(domain));
		return getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Update OK")
	public void update(@RequestBody T domain) {
		logger.info("method used");

		try {
			getService().update(domain);
		} catch (TransientObjectException e) {
			throw new IdentifierNullException();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Deprecated
	public void delete(@PathVariable Long id) {
		if (!getService().delete(id)) {
			throw new ObjectNotFoundException();
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
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK, reason = "Deleted")
	public void hibernateDelete(@PathVariable long id) {
		try {
			getService().hibernateDelete(id);
		} catch (HibernateOptimisticLockingFailureException e) {
			throw new ObjectNotFoundException();
		}
	}

	/**
	 * Represents the service
	 * 
	 * @return the service of a given type
	 */
	public abstract GenericService<T> getService();

	/**
	 * Adapter for xml presentation of a list
	 * 
	 * @return Arraylist of a given type
	 */
	public abstract ListAdapter<T> getList();

	/**
	 * EXCEPTIONHANDLING
	 * 
	 * How to retrieve the reason in a client:
	 * 
	 * } catch (HttpClientErrorException e) {
	 * assertEquals(HttpStatus.NOT_FOUND,e.getStatusCode()); } catch(Exception
	 * e) { fail("this isn't the expected exception: "+e.getMessage()); }
	 * 
	 */

	/**
	 * PUT
	 * Catches constraint violation exceptions Ex: Leader already added to
	 * committee
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Sorry, there is already an object with simular values")
	public void notUniqueObject() {
	}
	
	/**
	 * POST
	 * Catches constraint violation exceptions Ex: Leader already added to
	 * committee
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Sorry, there is already an object with simular values")
	public void notUniqueObject2() {
	}

	/**
	 * Catches illegal arguments Ex: When you try to insert a subject into a
	 * committee
	 */
	@ExceptionHandler(java.lang.IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Sorry, illegal arguments")
	public void notValidParams() {
	}

	/**
	 * Catches Jsypt exceptions
	 */
	@ExceptionHandler(EncryptionOperationNotPossibleException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Oops! Session token format is not correct")
	public void notAValidToken() {
		logger.warn("HAX");
	}

	/**
	 * Catches unvalid email requests
	 */
	@ExceptionHandler(UnvalidEmailException.class)
	@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED, reason = "That is not a correct email...")
	public void unvalidEmail() {
	}

	/**
	 * Catches unvalid email requests
	 */
	@ExceptionHandler(ExpiredTokenException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Your token has expired")
	public void tokenExpired() {
	}


}
package no.niths.application.rest;

import java.util.ArrayList;

import no.niths.application.rest.exception.IdentifierNullException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.ValidationHelper;
import no.niths.services.interfaces.GenericService;

import org.hibernate.TransientObjectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Abstract class that holds logic for CRUD operations
 * on a given domain type
 * 
 * @param <T> The type parameter
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
	@RequestMapping(value = { "{id}" }, 
		method = RequestMethod.GET, 
		headers = RESTConstants.ACCEPT_HEADER)
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
		getList().clear();
		getList().addAll(getService().getAll(domain));
		getList().setData(getList());

		ValidationHelper.isListEmpty(getList());

		return getList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK, reason = "Update ok")
	public void update(@RequestBody T domain) {
		logger.info("method used");

		
		try {
			getService().update(domain);
		}catch(TransientObjectException e){
			throw new IdentifierNullException();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK, reason = "Deleted")
	public void delete(@PathVariable Long id) {
		if (!getService().delete(id)) {
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

}

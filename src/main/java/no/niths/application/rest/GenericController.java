package no.niths.application.rest;

import java.util.ArrayList;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.GenericService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * T = domain
 * S = service
 */
public class GenericController<T,S extends GenericService<T>>
		implements RESTController<T> {

	@Autowired
	private S service;

	/**
	 * 
	 * @param Committee
	 *            The committee to be created
	 */
	@Override
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED, reason = "Committe created")
	public void create(@RequestBody T domain) {
		getService().create(domain);
	}

	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public T getById(@PathVariable Long id) {
		T c = getService().getById(id);
		if (c == null) {
			throw new ObjectNotFoundException("No object with id :" + id);
		}
		return c;
	}

	/**
	 * Implemented by each individual controller
	 */
	public ArrayList<T> getAll(T committee, HttpEntity<byte[]> request) {
		return null;
	}

	/**
	 * Implemented by each individual controller
	 */
	public void update(@RequestBody T committee, @PathVariable Long id) {

	}

	/**
	 * 
	 * @param long The id of the object to delete
	 */
	@Override
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK, reason = "Committe deleted")
	public void delete(@PathVariable Long id) {
		getService().delete(id);
	}

	public S getService() {
		return service;
	}

	public void setService(S service) {
		this.service = service;
	}
}

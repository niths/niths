package no.niths.application.rest;

import org.springframework.beans.factory.annotation.Autowired;

import no.niths.application.rest.interfaces.ExampleController;
import no.niths.application.rest.lists.ExampleList;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.domain.ExampleDomain;
import no.niths.services.interfaces.ExampleService;
import no.niths.services.interfaces.GenericService;

/**
 * 
 * Example of a controller implementation
 * <p>
 * Extends the AbstractRESTControllerImpl and implement
 * your controller interface. This will give you all
 * the basic CRUD operations
 * </p>
 * <p>
 * For a nice response format, create a new list (see ExampleList)
 * and return an instance of the list from getList
 * </p>
 *
 */
public class ExampleControllerImpl extends AbstractRESTControllerImpl<ExampleDomain>
						implements ExampleController{

	@Autowired
	private ExampleService service;
	
	private ExampleList list;
	
	@Override
	public GenericService<ExampleDomain> getService() {
		return service;
	}

	@Override
	public ListAdapter<ExampleDomain> getList() {
		return list;
	}
	

}

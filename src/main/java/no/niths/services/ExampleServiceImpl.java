package no.niths.services;

import org.springframework.beans.factory.annotation.Autowired;

import no.niths.domain.ExampleDomain;
import no.niths.infrastructure.interfaces.ExampleRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.interfaces.ExampleService;

/**
 * Example of a Service implementation
 * <p>
 * Extend the AbstractGenericService, and implement the
 * getRepository method that returns an instance of your
 * repository interface
 * </p>
 *
 */
public class ExampleServiceImpl extends AbstractGenericService<ExampleDomain>
							implements ExampleService{

	@Autowired
	private ExampleRepository repo;
	
	@Override
	public GenericRepository<ExampleDomain> getRepository() {
		return repo;
	}

}

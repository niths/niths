package no.niths.infrastructure;

import no.niths.domain.ExampleDomain;
import no.niths.infrastructure.interfaces.ExampleRepository;


/**
 * 
 * Example of a repository implementation
 * <p>
 * Extend the AbstractGenericRepositoryImpl, this will give
 * you the basic CRUD methods without the need to add any code
 * </p>
 * <p>
 * All repositories must implement the Repository interface (see ExampleRepository)
 * </p>
 */
public class ExampleRepositoryImpl extends AbstractGenericRepositoryImpl<ExampleDomain> 
                        implements ExampleRepository{

    public ExampleRepositoryImpl() {
        super(ExampleDomain.class, new ExampleDomain());
    }
    
}

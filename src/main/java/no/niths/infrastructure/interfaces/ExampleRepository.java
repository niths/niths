package no.niths.infrastructure.interfaces;

import no.niths.domain.ExampleDomain;

/**
 * 
 * Example repository interface
 * <p>
 * To create a new repository, create a interface like this
 * that extends GenericRepository with the type of you domain
 * </p>
 * <p>
 * See ExampleRepositoryImpl for implementation
 * </p>
 *
 */
public interface ExampleRepository extends GenericRepository<ExampleDomain>{

}

package no.niths.common.misc;

import java.util.List;
/**
 * A interface for Generic CRUD actions
 * @see AbstractGenericRepositoryImpl for information
 * @param <T>
 */
public interface GenericCRUDActions<T> {
	
	/**
	 * Persist a provided domain
	 * <p>
	 * @param domain the object you want to persist
	 * @return id of the domain
	 */
	Long create(T domain);

	/**
	 * Find and returns all objects which has values equal to the object sent as
	 * parameter.
	 * @param domain
	 *            - The object that has the values to search for
	 * @return List of objects found
	 */
	List<T> getAll(T domain);
	
	/**
	 * Find and returns all objects which has values equal to the object sent as parameter.
	 * The results are limited to between first and max.
	 * @param domain
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	List<T> getAll(T domain, int firstResult, int maxResults);
	
	/**
	 * Returns a domain on a given id
	 * @param id
	 * @return the domain with matching ID,
	 * 			or null if not found
	 */
	T getById(long id);
	
	/**
	 * Updates a existing domain
	 * <p>
	 * @param domain the object
	 */
	void update(T domain);

	/**
	 * Deletes a domain on a given id
	 * @param id
	 * @return true if update succeeded,
	 * 			false otherwise
	 */
	boolean delete(long id);
	
	/**
	 * Deletes a domain on a given id and deletes
	 * relationships.
	 * @param id the id of the object to delete
	 */
	void hibernateDelete(long id);
}
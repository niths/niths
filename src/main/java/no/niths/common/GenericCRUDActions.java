package no.niths.common;

import java.util.List;
/**
 * 
 * 
 *
 * @param <T>
 */
public interface GenericCRUDActions<T> {
	
	/**
	 * Persist a provided domain
	 * @param domain
	 * @return
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
	 * Returns a domain on a given id
	 * @param id
	 * @return
	 */
	T getById(long id);
	
	/**
	 * Updates a existing domain
	 * @param domain
	 */
	void update(T domain);

	/**
	 * Deletes a domain on a given id
	 * @param id
	 * @return
	 */
	boolean delete(long id);
	
	/**
	 * Deletes a domain on a given id and deletes
	 * relationships.
	 * @param id
	 */
	void hibernateDelete(long id);
}
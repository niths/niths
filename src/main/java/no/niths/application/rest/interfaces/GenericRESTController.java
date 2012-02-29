package no.niths.application.rest.interfaces;

import java.util.ArrayList;
import java.util.List;
/**
 * Generic interface for Controllers
 *
 * @param <T> The type parameter
 */
public interface GenericRESTController<T> {
 
	/**
	 * Persists the domain
	 * 
	 * @param domain
	 */
    public void create( T domain) ;
    
    /**
     * Returns the domain object with the given id
     * 
     * @param id the id of the domain object
     * @return the domain object
     */
    public T getById(Long id); 

    /**
     * Returns an arraylist with all domain objects of the type
     * 
     * @param domain the class type 
     * @return List of all domain objects
     */
    public ArrayList<T> getAll(T domain);

    /**
     * Updates the given domain object
     * 
     * @param domain the object to be updated
     */
    public void update(T domain);

    /**
     * Deletes the domain object with the given id
     * 
     * @param id the if of the domain object to be deleted
     */
    public void delete(Long id);
    
    /**
     * 
     * @param id
     */
    public void hibernateDelete(long id);
    
    /**
     * Clears the old list, fill the new list and checks if is empty.
     * @param list
     */
    public void renewList(List<T> list);
    
}

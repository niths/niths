package no.niths.services;

import java.util.List;

public class GenericService<T> {
	
	public void create(T domain) {
		
	}

	/**
	 * 
	 * @return
	 */
	public List<T> getAll(T domain) {
		return null;
	}
	
	
	/**
	 * 
	 * @param cid
	 * @return
	 */
	public T getById(long id) {
		return null;
	}



	/**
	 * 
	 * @param committee
	 */
	public void update(T doamin) {
		
	}
	
	/**
	 * 
	 * @param committee
	 */
	public void delete(long id) {
		
	}


}

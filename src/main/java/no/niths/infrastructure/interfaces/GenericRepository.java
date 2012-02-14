package no.niths.infrastructure.interfaces;

import java.util.List;



public interface GenericRepository <T> {
	
	Long create(T domain);
	
	List<T> getAll(T domain);
	
	T getById(long id);

	void update(T domain);
	
	boolean delete(long id);
}

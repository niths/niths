package no.niths.common;

import java.util.List;

public interface GenericCRUDActions<T> {
	
	Long create(T domain);

	List<T> getAll(T domain);

	T getById(long id);

	void update(T domain);

	boolean delete(long id);
}

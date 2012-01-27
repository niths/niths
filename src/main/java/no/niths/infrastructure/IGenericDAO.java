package no.niths.infrastructure;

import no.niths.domain.Course;

public interface IGenericDAO<T> {

	void setClazz(Class<T> class1);

}

package no.niths.application.rest.lists;

import java.util.List;

public interface Exportable<T> {

	void setData(List<T> list);
}
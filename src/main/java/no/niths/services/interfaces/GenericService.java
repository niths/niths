package no.niths.services.interfaces;

import no.niths.common.GenericCRUDActions;

public interface GenericService <T> extends GenericCRUDActions<T> {
	void mergeUpdate(T domain);
}
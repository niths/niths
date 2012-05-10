package no.niths.services.interfaces;

import no.niths.common.misc.GenericCRUDActions;
/**
 * Generic interface for Services
 * 
 * @param <T> the domain object type
 */
public interface GenericService <T> extends GenericCRUDActions<T> {
    void mergeUpdate(T domain);
}
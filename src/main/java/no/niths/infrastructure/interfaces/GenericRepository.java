package no.niths.infrastructure.interfaces;

import no.niths.common.misc.GenericCRUDActions;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;

/**
 * Generic repository class
 * 
 * @see GenericCRUDActions
 * @see AbstractGenericRepositoryImpl
 * @param <T> The domain type
 */
public interface GenericRepository<T> extends GenericCRUDActions<T>{

}

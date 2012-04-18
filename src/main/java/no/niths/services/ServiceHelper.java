package no.niths.services;

import java.util.List;

import no.niths.application.rest.exception.ObjectInCollectionException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.helper.Error;
import no.niths.common.MessageProvider;
import no.niths.common.ValidationHelper;
import no.niths.domain.Domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceHelper<T> {
	private Logger logger = LoggerFactory.getLogger(ServiceHelper.class);
	
	/**
	 * Helper method for checking if the the list element is a instance of
	 * Domain and then we can cast the list element to a Domain for using the
	 * getId() method
	 * 
	 * @throws ObjectInCollectionException
	 *             () if the object is found
	 * @param list
	 * @param id
	 */
	@SuppressWarnings("rawtypes")
	public void checkIfObjectIsInCollection(List list, long id,Class clazz) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof Domain) {
				Domain d = (Domain) list.get(i);
				if (d.getId() == id) {
					throw new ObjectInCollectionException(
							MessageProvider.buildErrorMsg(clazz,
									Error.OBJECT_IN_COLLECTION));
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public T validate(T domain, Class clazz) {
		ValidationHelper.isObjectNull(domain, clazz);
		return domain;
	}

	@SuppressWarnings("rawtypes")
	public void checkIfIsRemoved(boolean isRemoved, Class clazz) {
		if (!isRemoved) {
			String msg = MessageProvider.buildErrorMsg(clazz, Error.NOT_FOUND);
			logger.debug(msg);
			throw new ObjectNotFoundException(msg);
		}
	}
}

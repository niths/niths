package no.niths.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import no.niths.domain.APIEvent;
import no.niths.infrastructure.interfaces.APIEventRepository;
import no.niths.services.interfaces.APIEventService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for APIEvents To persist the event, annotate any public method
 * with @ApiEvent(title = "title).
 * 
 * The method must be public and take one parameter.
 * 
 * How to use:
 * 
 * @ApiEvent(title="Something happened") public void anyMethod(Object obj){}
 * 
 * 
 */
@Service
@Transactional
public class APIEventServiceImpl implements APIEventService {

	private Logger logger = LoggerFactory.getLogger(APIEventServiceImpl.class);
	
	@Autowired
	private APIEventRepository repo;

	private CustomBeanUtilsBean beanCopyer = new CustomBeanUtilsBean();

	public Long create(APIEvent event) {
		return repo.create(event);
	}

	public APIEvent getById(long id) {
		return repo.getById(id);
	}

	public List<APIEvent> getAll(APIEvent event) {
		return repo.getAll(event);
	}

	public void update(APIEvent event) {
		APIEvent apiEventToUpdate = repo.getById(event.getId());	
		try {
			beanCopyer.copyProperties(apiEventToUpdate, event);
		} catch (IllegalAccessException | InvocationTargetException e1) {
			logger.error("error",e1);
			e1.printStackTrace();
		}
		repo.update(apiEventToUpdate);
	}

	public boolean delete(long id) {
		return repo.delete(id);
	}

	@Override
	public void hibernateDelete(long id) {
		repo.hibernateDelete(id);
	}
}
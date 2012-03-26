package no.niths.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import no.niths.domain.Domain;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.interfaces.GenericService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractGenericService<T extends Domain> implements GenericService<T>{

	private Logger logger = LoggerFactory.getLogger(AbstractGenericService.class);
	private CustomBeanUtilsBean mergeBean = new CustomBeanUtilsBean();
	
	@Override
	public Long create(T domain) {
		return getRepository().create(domain);
	}

	@Override
	public List<T> getAll(T domain){
		return getRepository().getAll(domain);
	}
	
	@Override
	public T getById(long id){
		return getRepository().getById(id);
	}

	@Override
	public void update(T domain) {
		getRepository().update(domain);
	}
	
	
	public void mergeUpdate(T domain) {
		T domaineToUpdate = getRepository().getById(domain.getId());
		try {
			mergeBean.copyProperties(domaineToUpdate, domain);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error",e);
			e.printStackTrace();
		}
		getRepository().update(domaineToUpdate);
	}

	@Override
	public boolean delete(long id) {
		return getRepository().delete(id);
	}

	@Override
	public void hibernateDelete(long id) {
		getRepository().hibernateDelete(id);
	}
	
	public abstract GenericRepository<T> getRepository();

}

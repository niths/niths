package no.niths.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import no.niths.domain.signaling.AccessField;
import no.niths.infrastructure.interfaces.AccessFieldRepository;
import no.niths.services.interfaces.AccessFieldService;

@Service
@Transactional
public class AccessFieldServiceImpl implements AccessFieldService{
	
	private Logger logger = LoggerFactory.getLogger(AccessFieldServiceImpl.class);
	private CustomBeanUtilsBean copyBean = new CustomBeanUtilsBean();
	
	@Autowired
	private AccessFieldRepository repo;
	
	@Override
	public Long create(AccessField domain) {
		return repo.create(domain);
	}

	@Override
	public List<AccessField> getAll(AccessField domain) {
		return repo.getAll(domain);
	}

	@Override
	public AccessField getById(long id) {
		return repo.getById(id);
	}

	@Override
	public void update(AccessField domain) {
		AccessField afToUpdate = repo.getById(domain.getId());
		try {
			copyBean.copyProperties(afToUpdate, domain);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error",e);
			e.printStackTrace();
		}
		repo.update(afToUpdate);		
	}

	@Override
	public boolean delete(long id) {
		return repo.delete(id);
	}

	@Override
	public void hibernateDelete(long id) {
		repo.hibernateDelete(id);
		
	}
	
}
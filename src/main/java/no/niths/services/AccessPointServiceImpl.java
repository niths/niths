package no.niths.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import no.niths.domain.signaling.AccessPoint;
import no.niths.infrastructure.interfaces.AccessPointRepository;
import no.niths.services.interfaces.AccessPointService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccessPointServiceImpl implements AccessPointService {

	private Logger logger = LoggerFactory.getLogger(AccessPointServiceImpl.class);
	private CustomBeanUtilsBean copyBean = new CustomBeanUtilsBean();
	
    @Autowired
    private AccessPointRepository repo;

    @Override
    public Long create(AccessPoint accessPoint) {
        return repo.create(accessPoint);
    }

    @Override
    public List<AccessPoint> getAll(AccessPoint accessPoint) {
        return repo.getAll(accessPoint);
    }

    @Override
    public AccessPoint getById(long id) {
        return repo.getById(id);
    }

    @Override
    public void update(AccessPoint accessPoint) {
    	AccessPoint apToUpdate = repo.getById(accessPoint.getId());
    	try {
			copyBean.copyProperties(apToUpdate, accessPoint);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error",e);
			e.printStackTrace();
		}
        repo.update(apToUpdate);
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
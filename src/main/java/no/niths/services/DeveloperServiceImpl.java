package no.niths.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import no.niths.aop.ApiEvent;
import no.niths.domain.Developer;
import no.niths.infrastructure.interfaces.DeveloperRepository;
import no.niths.services.interfaces.DeveloperService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeveloperServiceImpl implements DeveloperService{

	private Logger logger = LoggerFactory.getLogger(DeveloperServiceImpl.class);

	private CustomBeanUtilsBean beanCopy = new CustomBeanUtilsBean();

    @Autowired
    private DeveloperRepository repo;

    @ApiEvent(title = "Developer created")
    public Long create(Developer dev) {
        return repo.create(dev);
    }

    public Developer getById(long id) {
    	Developer dev = repo.getById(id);
    	if(dev != null) {
    		dev.getApps().size();
    	}
    	return dev;
   }

    public List<Developer> getAll(Developer dev) {
    	return repo.getAll(dev);
    }

    @ApiEvent(title = "Developer updated")
    public void update(Developer dev) {
	
    	Developer developerToUpdate = repo.getById(dev.getId());
		try {
			beanCopy.copyProperties(developerToUpdate, dev);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error",e);
			e.printStackTrace();
		}
		repo.update(developerToUpdate);
    }

    public boolean delete(long id) {
        return repo.delete(id);
    }

	@Override
	public void hibernateDelete(long id) {
		repo.hibernateDelete(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Developer getDeveloperByDeveloperToken(String token, boolean isEnabled) {
		return repo.getByDeveloperToken(token, isEnabled);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Developer> getAllWithApps(Developer dev) {
		List<Developer> all = repo.getAll(dev);
		for (Developer d: all){
			d.getApps().size();
		}
		return all;
	}

	@Override
	public void updateForDeveloperController(Developer dev) {
    	repo.update(dev);	
	}

}
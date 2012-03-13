package no.niths.services;

import java.util.List;

import no.niths.aop.ApiEvent;
import no.niths.domain.Application;
import no.niths.infrastructure.interfaces.ApplicationRepository;
import no.niths.services.interfaces.ApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService{

    @Autowired
    private ApplicationRepository repo;

    @ApiEvent(title = "Application created")
    public Long create(Application app) {
        return repo.create(app);
    }

    public Application getById(long id) {
    	return repo.getById(id);
   }

    public List<Application> getAll(Application app) {
    	return repo.getAll(app);
    }

    @ApiEvent(title = "Application updated")
    public void update(Application app) {
        repo.update(app);
    }

    public boolean delete(long id) {
        return repo.delete(id);
    }

	@Override
	public void hibernateDelete(long id) {
		repo.hibernateDelete(id);
	}
}
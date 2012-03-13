package no.niths.services;

import java.util.List;

import no.niths.aop.ApiEvent;
import no.niths.domain.Developer;
import no.niths.infrastructure.interfaces.DeveloperRepository;
import no.niths.services.interfaces.DeveloperService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeveloperServiceImpl implements DeveloperService{

    @Autowired
    private DeveloperRepository repo;

    @ApiEvent(title = "Developer created")
    public Long create(Developer dev) {
        return repo.create(dev);
    }

    public Developer getById(long id) {
    	return repo.getById(id);
   }

    public List<Developer> getAll(Developer dev) {
    	return repo.getAll(dev);
    }

    @ApiEvent(title = "Developer updated")
    public void update(Developer dev) {
        repo.update(dev);
    }

    public boolean delete(long id) {
        return repo.delete(id);
    }

	@Override
	public void hibernateDelete(long id) {
		repo.hibernateDelete(id);
	}
}
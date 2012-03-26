package no.niths.services;

import no.niths.domain.Application;
import no.niths.infrastructure.interfaces.ApplicationRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.interfaces.ApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl extends AbstractGenericService<Application> implements ApplicationService{
	
    @Autowired
    private ApplicationRepository repo;
   
	@Override
	public Application getByApplicationToken(String token){
		return repo.getByApplicationToken(token);
	}

	@Override
	public GenericRepository<Application> getRepository() {
		return repo;
	}
}
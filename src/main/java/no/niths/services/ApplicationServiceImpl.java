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
	@Deprecated
	public Application getByApplicationToken(String token){
		return repo.getByApplicationToken(token);
	}
	
	/**
	 * Returns the application matching the key
	 * The application must be enabled to be returned
	 * 
	 * @param key the application key as a string
	 * @return the application or null if no matching key or app is not enabled
	 */
	@Override
	public Application getByApplicationKey(String key){
		return repo.getByApplicationKey(key);
	}

	@Override
	public GenericRepository<Application> getRepository() {
		return repo;
	}
}
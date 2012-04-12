package no.niths.services;

import no.niths.domain.signaling.AccessPoint;
import no.niths.infrastructure.interfaces.AccessPointRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.interfaces.AccessPointService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessPointServiceImpl extends AbstractGenericService<AccessPoint> implements AccessPointService {
	
    @Autowired
    private AccessPointRepository repo;

	@Override
	public GenericRepository<AccessPoint> getRepository() {
		return repo;
	}
	
	@Override
	public AccessPoint getById(long id) {
		AccessPoint ap = super.getById(id);
		if(ap != null){
			ap.getAccessFields().size();
		}
		return super.getById(id);
	}
}
package no.niths.services;

import no.niths.domain.signaling.AccessField;
import no.niths.infrastructure.interfaces.AccessFieldRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.interfaces.AccessFieldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessFieldServiceImpl extends AbstractGenericService<AccessField>
		implements AccessFieldService {

	@Autowired
	private AccessFieldRepository repo;

	@Override
	public GenericRepository<AccessField> getRepository() {
		return repo;
	}

	
	@Override
	public AccessField getById(long id) {
		AccessField af= super.getById(id);
		if(af != null){
			af.getRooms().size();
			if(af.getAccessPoint() != null){
				af.getAccessPoint().getAddress();
			}
		}
		
		return af;
	}
}
package no.niths.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import no.niths.domain.signaling.AccessField;
import no.niths.services.interfaces.AccessFieldService;

@Service
@Transactional
public class AccessFieldServiceImpl implements AccessFieldService{

	
	
	@Override
	public Long create(AccessField domain) {
		return null;
	}

	@Override
	public List<AccessField> getAll(AccessField domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccessField getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(AccessField domain) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void hibernateDelete(long id) {
		// TODO Auto-generated method stub
		
	}
	
}
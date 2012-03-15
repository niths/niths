package no.niths.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import no.niths.domain.signaling.AccessField;
import no.niths.infrastructure.interfaces.AccessFieldRepository;
import no.niths.services.interfaces.AccessFieldService;

@Service
@Transactional
public class AccessFieldServiceImpl implements AccessFieldService{
	
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
		repo.update(domain);
		
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
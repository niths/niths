package no.niths.services;

import no.niths.domain.Committee;
import no.niths.infrastructure.interfaces.CommitteeRepositorty;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.interfaces.CommitteeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommitteeServiceImpl extends AbstractGenericService<Committee>
		implements CommitteeService {

	@Autowired	
	private CommitteeRepositorty repo;

	@Override
	public GenericRepository<Committee> getRepository() {
		return repo;
	}

}

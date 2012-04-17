package no.niths.services.school;

import no.niths.domain.school.Committee;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.school.interfaces.CommitteeRepositorty;
import no.niths.services.AbstractGenericService;
import no.niths.services.school.interfaces.CommitteeService;

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

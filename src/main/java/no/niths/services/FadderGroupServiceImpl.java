package no.niths.services;

import no.niths.domain.FadderGroup;
import no.niths.infrastructure.interfaces.FadderGroupRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.interfaces.FadderGroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FadderGroupServiceImpl extends AbstractGenericService<FadderGroup> implements FadderGroupService {

	@Autowired
	private FadderGroupRepository repo;

	public FadderGroup getById(long id) {
		FadderGroup group = repo.getById(id);
		if (group != null) {
			group.getFadderChildren().size();
			group.getLeaders().size();
		}
		return group;
	}

	@Override
	public GenericRepository<FadderGroup> getRepository() {
		return repo;
	}
}
package no.niths.services.school;

import no.niths.domain.school.FadderGroup;
import no.niths.infrastructure.interfaces.FadderGroupRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.school.interfaces.FadderGroupService;

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

	@Override
	public FadderGroup getGroupBelongingToStudent(Long studentId) {
		FadderGroup group =  repo.getGroupBelongingToStudent(studentId);
		if (group != null) {
			group.getFadderChildren().size();
			group.getLeaders().size();
		}
		return group;
	}
}
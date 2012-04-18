package no.niths.services.school;

import java.util.List;

import no.niths.domain.school.FadderGroup;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.school.interfaces.FadderGroupRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.school.interfaces.FadderGroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FadderGroupServiceImpl extends AbstractGenericService<FadderGroup>
		implements FadderGroupService {

	@Autowired
	private FadderGroupRepository repo;

	@Override
	public GenericRepository<FadderGroup> getRepository() {
		return repo;
	}

	@Override
	public List<FadderGroup> getAll(FadderGroup domain) {
		List<FadderGroup> list = repo.getAll(domain);
		for (FadderGroup fg : list) {
			fg.getLeaders().size();
		}
		return list;
	}

}
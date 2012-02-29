package no.niths.services;

import java.util.List;

import no.niths.domain.FadderGroup;
import no.niths.domain.Subject;
import no.niths.infrastructure.interfaces.FadderGroupRepository;
import no.niths.infrastructure.interfaces.SubjectRepository;
import no.niths.services.interfaces.FadderGroupService;
import no.niths.services.interfaces.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FadderGroupServiceImpl implements FadderGroupService{

    @Autowired
    private FadderGroupRepository repo;

    public Long create(FadderGroup group) {
        return repo.create(group);
    }

    public FadderGroup getById(long id) {
    	return repo.getById(id);
   }

    public List<FadderGroup> getAll(FadderGroup group) {
    	return repo.getAll(group);
    }

    public void update(FadderGroup group) {
        repo.update(group);
    }

    public boolean delete(long id) {
        return repo.delete(id);
    }

	@Override
	public void hibernateDelete(long id) {
		repo.hibernateDelete(id);
	}
}
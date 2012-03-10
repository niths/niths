package no.niths.services;

import java.util.ArrayList;
import java.util.List;

import no.niths.domain.FadderGroup;
import no.niths.domain.Student;
import no.niths.infrastructure.interfaces.FadderGroupRepository;
import no.niths.services.interfaces.FadderGroupService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FadderGroupServiceImpl implements FadderGroupService{

	Logger logger = org.slf4j.LoggerFactory.getLogger(FadderGroupServiceImpl.class);
	
    @Autowired
    private FadderGroupRepository repo;

    public Long create(FadderGroup group) {
        return repo.create(group);
    }

    public FadderGroup getById(long id) {
    	FadderGroup group = repo.getById(id);
    	if(group != null){
    		group.getFadderChildren().size();
    		group.getLeaders().size();    		
    	}
    	return group;
   }

    public List<FadderGroup> getAll(FadderGroup group) {
    	List<FadderGroup> all = repo.getAll(group);
    	return all;
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
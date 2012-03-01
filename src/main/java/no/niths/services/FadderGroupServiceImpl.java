package no.niths.services;

import java.util.ArrayList;
import java.util.List;

import no.niths.domain.FadderGroup;
import no.niths.domain.Student;
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeAChildrenFromAGroup(Student child, FadderGroup group) {
		group.getFadderChildren().remove(child);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeALeaderFromAGroup(Student leader, FadderGroup group) {
		group.getLeaders().remove(leader);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeAllChildrenFromGroup(FadderGroup group) {
		if(!(group.getFadderChildren().isEmpty())){
			group.setFadderChildren(new ArrayList<Student>());
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeAllLeadersFromGroup(FadderGroup group) {
		if(!(group.getLeaders().isEmpty())){
			group.setLeaders(new ArrayList<Student>());
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addLeaderToGroup(Student leader, FadderGroup group) {
		group.getLeaders().add(leader);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addChildrenToGroup(Student child, FadderGroup group) {
		group.getFadderChildren().add(child);
		
	}


}
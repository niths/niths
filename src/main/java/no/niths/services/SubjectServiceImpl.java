package no.niths.services;

import java.util.List;

import no.niths.aop.ApiEvent;
import no.niths.domain.Subject;
import no.niths.infrastructure.interfaces.SubjectRepository;
import no.niths.services.interfaces.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService{

    @Autowired
    private SubjectRepository repo;

    @ApiEvent(title = "Subject created")
    public Long create(Subject topic) {
        return repo.create(topic);
    }

    public Subject getById(long id) {
    	return repo.getById(id);
   }

    public List<Subject> getAll(Subject topic) {
    	return repo.getAll(topic);
    }

    @ApiEvent(title = "Subject updated")
    public void update(Subject topic) {
        repo.update(topic);
    }

    public boolean delete(long id) {
        return repo.delete(id);
    }

	@Override
	public void hibernateDelete(long id) {
		repo.hibernateDelete(id);
	}
}
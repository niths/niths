package no.niths.services;

import no.niths.domain.Subject;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.interfaces.SubjectRepository;
import no.niths.services.interfaces.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl extends AbstractGenericService<Subject>
		implements SubjectService {

	@Autowired
	private SubjectRepository repo;

	public Subject getById(long id) {
		Subject s = repo.getById(id);
		s.getTutors().size();
		return s;
	}

	@Override
	public GenericRepository<Subject> getRepository() {
		return repo;
	}

}
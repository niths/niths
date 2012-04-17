package no.niths.services.school;

import no.niths.domain.school.Subject;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.interfaces.SubjectRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.school.interfaces.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl extends AbstractGenericService<Subject>
		implements SubjectService {

	@Autowired
	private SubjectRepository repo;

	public Subject getById(long id) {
		Subject s = repo.getById(id);
		if(s!= null){
			s.getTutors().size();
			if(s.getRoom() != null){
				s.getRoom().getRoomName();
			}
		}
		
		return s;
	}

	@Override
	public GenericRepository<Subject> getRepository() {
		return repo;
	}

}
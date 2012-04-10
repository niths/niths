package no.niths.services;

import no.niths.domain.Exam;
import no.niths.infrastructure.interfaces.ExamRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.interfaces.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExamServiceImpl extends AbstractGenericService<Exam> implements
		ExamService {

	@Autowired
	private ExamRepository examRepository;

	public Exam getById(long id) {
		Exam exam = examRepository.getById(id);
		if (exam != null) {
			exam.getRooms().size();
			if (exam.getSubject() != null) {
				exam.getSubject().getEndTime();
			}
		}
		return exam;
	}

	@Override
	public GenericRepository<Exam> getRepository() {
		return examRepository;
	}
}

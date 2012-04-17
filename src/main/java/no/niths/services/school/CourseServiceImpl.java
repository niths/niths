package no.niths.services.school;

import no.niths.domain.school.Course;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.school.interfaces.CourseRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.school.interfaces.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends AbstractGenericService<Course> implements
		CourseService {

	@Autowired
	private CourseRepository repo;

	@Override
	public GenericRepository<Course> getRepository() {
		return repo;
	}

}

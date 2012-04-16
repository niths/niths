package no.niths.services;

import no.niths.domain.Course;
import no.niths.infrastructure.interfaces.CourseRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.services.interfaces.CourseService;

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

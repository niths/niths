package no.niths.infrastructure;

import no.niths.domain.Course;
import no.niths.infrastructure.interfaces.CoursesRepository;

import org.springframework.stereotype.Repository;

@Repository
public class CoursesRepositoryImpl extends GenericRepositoryImpl<Course>
		implements CoursesRepository<Course> {

	public CoursesRepositoryImpl() {
		super(Course.class);
	}

}
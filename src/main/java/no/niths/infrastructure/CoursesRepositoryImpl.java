package no.niths.infrastructure;

import no.niths.domain.Course;
import no.niths.infrastructure.interfaces.CourseRepository;

import org.springframework.stereotype.Repository;

@Repository
public class CoursesRepositoryImpl extends AbstractGenericRepositoryImpl<Course>
		implements CourseRepository {

	public CoursesRepositoryImpl() {
		super(Course.class, new Course());
	}
}
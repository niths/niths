package no.niths.infrastructure.school;

import no.niths.domain.school.Course;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.school.interfaces.CourseRepository;

import org.springframework.stereotype.Repository;
/**
 * Repository class for Course
 *
 * <p>
 * Inherits the basic CRUD actions
 * </p>
 */
@Repository
public class CoursesRepositoryImpl extends AbstractGenericRepositoryImpl<Course>
        implements CourseRepository {

    public CoursesRepositoryImpl() {
        super(Course.class, new Course());
    }
}
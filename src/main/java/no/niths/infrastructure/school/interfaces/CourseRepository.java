package no.niths.infrastructure.school.interfaces;

import no.niths.domain.school.Course;
import no.niths.infrastructure.interfaces.GenericRepository;

/**
 * Repository class for Course
 *
 * <p>
 * Inherits the basic CRUD actions
 * </p>
 */
public interface CourseRepository  extends GenericRepository<Course> {
}
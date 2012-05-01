package no.niths.services.school.interfaces;

import no.niths.domain.school.Course;
import no.niths.services.interfaces.GenericService;
/**
 * Service Class for Course
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for addRepresentative,
 * removeRepresentative,
 * addSubject and removeSubject
 * </p>
 */
public interface CourseService extends GenericService<Course> {

    /**
     * Adds a representative (Student) to the course
     * @param courseId id for course
     * @param studentId id for student
     */
    void addRepresentative(Long courseId, Long studentId);

    /**
     * Removes a representative (Student) from a course
     * @param courseId id for course
     * @param studentId id for student
     */
    void removeRepresentative(Long courseId, Long studentId);

    /**
     * Adds a subject to the course
     * @param courseId id for course
     * @param subjectId id for subject
     */
    void addSubject(Long courseId, Long subjectId);

    /**
     * Removes a subject from a course
     * @param courseId id for course
     * @param subjectId id for subject
     */
    void removeSubject(Long courseId, Long subjectId);
}
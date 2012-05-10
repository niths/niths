package no.niths.infrastructure.school.interfaces;

import java.util.List;

import no.niths.common.misc.Searchable;
import no.niths.domain.school.Student;
import no.niths.infrastructure.interfaces.GenericRepository;

/**
 * Repository class for Student
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for getStudentsWithNamedCourse
 * and getStudentByColumn
 * </p>
 */
public interface StudentRepository extends GenericRepository<Student>{

    /**
     * Returns all student in the given course
     * 
     * @param name The course name
     * @return list of students in the course
     */
    List<Student> getStudentsWithNamedCourse(String name);
    
    /**
     * Returns all students with matching attribute
     * Columns must be annotated with @Searchable
     * 
     * @see Searchable
     * 
     * @param column the attribute to search for
     * @param criteria the search query
     * @return List of matching students
     * 
     */
    List<Student> getStudentByColumn(String column, String criteria);

}

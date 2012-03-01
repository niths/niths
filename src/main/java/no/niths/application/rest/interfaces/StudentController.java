package no.niths.application.rest.interfaces;

import java.util.List;

import no.niths.domain.Course;
import no.niths.domain.Student;

/**
 * Controller for student
 *
 */
public interface StudentController extends GenericRESTController<Student> {

	/**
	 * HOW TO USE SECURITY ANNOTATIONS:
	 * 
	 * One role allowed:
	 * @PreAuthorize("hasRole('ROLE_ADMIN')")
	 * Multiple roles allowed:
	 * @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEST')")
	 * 
	 */
//  EXAMPLE OF HOW TO USE:	
//	@Override
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEST')")
//	public ArrayList<Student> getAll(Student domain);
	
	/**
	 * Returns a list of all students in a given course
	 * @param course the course to get students from
	 * @return list of students
	 */
	public List<Student> getStudentsWithNamedCourse(Course course);
	
}

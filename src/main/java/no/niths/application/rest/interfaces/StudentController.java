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
	
	/**
	 * Adds a student to a orientation group
	 * 
	 * @param studentId The id of the student
	 * @param groupId The group id of the orientation group
	 */
	public void addStudentToFadderGroup(long studentId, int groupId);
	
	/**
	 * Return all students in an orientation group
	 * 
	 * @return list of all students in an orientation group
	 */
	public List<Student> getAllStudentsInFadderGroup();
	/**
	 * Returns all students in a specific group
	 * 
	 * @param groupId id of the group
	 * @return list of all students
	 */
	public List<Student> getAllStudentsInAFadderGroupWithId(int groupId);
	
	/**
	 * Removes a student from a group
	 * 
	 * @param studentId Id of the student
	 * @param groupId id of the group
	 */
	public void removeStudentFromFadderGroup(long studentId, int groupId);
	
	/**
	 * Removes a student from all groups he/she is a member of
	 * 
	 * @param studentId id of the students to remove
	 */
	public void removeStudentFromAllFadderGroups(long studentId);
}

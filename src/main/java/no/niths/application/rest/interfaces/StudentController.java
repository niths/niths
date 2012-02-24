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
	 * Returns a list of all students in a given course
	 * @param course the course to get students from
	 * @return list of students
	 */
	public List<Student> getStudentsWithNamedCourse(Course course);
	
	/**
	 * Adds a student as a mentor
	 * 
	 * @param studentId The id of the student
	 * @param groupId The group id of the mentor
	 */
	public void addStudentToOrientationGroup(long studentId,int groupId);
	
	/**
	 * Return all mentors
	 * 
	 * @return list of all mentors
	 */
	public List<Student> getAllOrientationGroups();
	/**
	 * Returns all mentors in a group
	 * 
	 * @param groupId id of the group
	 * @return list of all mentors
	 */
	public List<Student> getStudentsInOrientationGroup(int groupId);
	
	/**
	 * Removes a mentor from a group
	 * 
	 * @param studentId Id of the mentor
	 * @param groupId id of the group
	 */
	public void removeStudentFromOrientationGroup(long studentId,int groupId);
	
	/**
	 * Removes a mentor from all groups
	 * 
	 * @param studentId id of the mentor to remove
	 */
	public void removeStudentFromAllOrientationGroups(long studentId);
}

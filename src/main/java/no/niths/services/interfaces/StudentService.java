package no.niths.services.interfaces;

import java.util.List;

import no.niths.domain.Student;

public interface StudentService extends GenericService<Student> {

	public List<Student> getStudentsWithNamedCourse(String name);
	
	public void addStudentToOrientationGroup(Student student,int groupId);
	
	public List<Student> getAllStudentsInAnOrientationGroup();
	
	public List<Student> getAllStudentsInAOrientationGroup(int groupId);
	
	public void removeStudentFromOrientationGroup(Student student,int groupId);
	
	public void removeStudentFromAllOrientationGroups(Student student);
}

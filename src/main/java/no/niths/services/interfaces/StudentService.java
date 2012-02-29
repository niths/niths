package no.niths.services.interfaces;

import java.util.List;

import no.niths.domain.Student;

public interface StudentService extends GenericService<Student> {

	public List<Student> getStudentsWithNamedCourse(String name);
	
//	public void addStudentToFadderGroup(Student student, int groupId);
//	
//	public List<Student> getAllStudentsInFadderGroup();
//	
//	public List<Student> getAllStudentsInAFadderGroupWithId(int groupId);
//	
//	public void removeStudentFromFadderGroup(Student student, int groupId);
//	
//	public void removeStudentFromAllFadderGroups(Student student);
}

package no.niths.services.interfaces;

import java.util.List;

import no.niths.domain.Student;

public interface StudentService extends GenericService<Student> {

	public List<Student> getStudentsWithNamedCourse(String name);
	
	public void addStudentToFadderUka(Student student, int groupId);
	
	public List<Student> getAllStudentsInAFadderUka();
	
	public List<Student> getAllStudentsInFadderUkaBelongingToAGroup(int groupId);
	
	public void removeStudentFromFadderUka(Student student, int groupId);
	
	public void removeStudentFromAllOfFadderUka(Student student);
}

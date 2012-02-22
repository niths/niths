package no.niths.services.interfaces;

import java.util.List;

import no.niths.domain.Student;

public interface StudentService extends GenericService<Student> {

	public List<Student> getStudentsWithNamedCourse(String name);
	
	public void addStudentToMentor(Student student,int groupId);
	
	public List<Student> getAllMentors();
	
	public List<Student> getMentorsByGroupe(int groupId);
	
	public void removeStudentFromMentorGroup(Student student,int groupId);
	
	public void removeStudentFromAllMentorGroups(Student student);
}

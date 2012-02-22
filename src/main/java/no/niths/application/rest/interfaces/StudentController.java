package no.niths.application.rest.interfaces;

import java.util.List;

import no.niths.domain.Course;
import no.niths.domain.Student;

public interface StudentController extends GenericRESTController<Student> {

	public List<Student> getStudentsWithNamedCourse(Course course);
	
	public void addStudentToMentor(long studentId,int groupId);
	
	public List<Student> getAllMentors();
	
	public List<Student> getMentorsByGroupe(int groupId);
	
	public void removeStudentFromMentorGroup(long studentId,int groupId);
	
	public void removeStudentFromAllMentorGroups(long studentId);
}

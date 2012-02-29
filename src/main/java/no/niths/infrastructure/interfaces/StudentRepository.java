package no.niths.infrastructure.interfaces;

import java.util.List;

import no.niths.domain.Student;


public interface StudentRepository extends GenericRepository<Student>{

	public List<Student> getStudentsWithNamedCourse(String name);
	
	public Student getStudentByEmail(String email);
	
	public List<Student> getAllStudentsInAFadderUka();

	public List<Student> getAllStudentsInFadderUkaBelongingToAGroup(int groupId) ;
}

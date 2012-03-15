package no.niths.services.interfaces;

import java.util.List;

import no.niths.domain.Student;

public interface StudentService extends GenericService<Student> {

	List<Student> getStudentsWithNamedCourse(String name);
	
	List<Student> getStudentsAndRoles(Student student);
	
	List<Student> getStudentByColumn(String column, String criteria);
	
	Student getStudentBySessionToken(String token);
	
	Student getStudentByEmail(String email);
}
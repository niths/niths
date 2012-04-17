package no.niths.infrastructure.interfaces;

import java.util.List;

import no.niths.domain.school.Student;


public interface StudentRepository extends GenericRepository<Student>{

	List<Student> getStudentsWithNamedCourse(String name);
	
//	Student getStudentByEmail(String email);
//
//	Student getStudentBySessionToken(String sessionToken);
	
	List<Student> getStudentByColumn(String column, String criteria);

}

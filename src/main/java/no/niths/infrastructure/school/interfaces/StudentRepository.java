package no.niths.infrastructure.school.interfaces;

import java.util.List;

import no.niths.domain.school.Student;
import no.niths.infrastructure.interfaces.GenericRepository;


public interface StudentRepository extends GenericRepository<Student>{

	List<Student> getStudentsWithNamedCourse(String name);
	
//	Student getStudentByEmail(String email);
//
//	Student getStudentBySessionToken(String sessionToken);
	
	List<Student> getStudentByColumn(String column, String criteria);

}

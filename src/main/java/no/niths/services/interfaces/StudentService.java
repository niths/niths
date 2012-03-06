package no.niths.services.interfaces;

import java.util.List;

import no.niths.domain.Student;

public interface StudentService extends GenericService<Student> {

	List<Student> getStudentsWithNamedCourse(String name);
	
	List<Student> getStudentsAndRoles(Student s);
	
	List<Student> getStudentByColumn(String column, String criteria);
}

package no.niths.services.interfaces;

import java.util.List;

import no.niths.domain.Student;

public interface StudentService extends GenericService<Student> {

	List<Student> getStudentsWithNamedCourse(String name);
}

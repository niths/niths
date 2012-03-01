package no.niths.services.interfaces;

import java.util.List;

import no.niths.domain.Student;

public interface StudentService extends GenericService<Student> {

	public List<Student> getStudentsWithNamedCourse(String name);

}

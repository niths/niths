package no.niths.application.rest.interfaces;

import java.util.List;

import no.niths.domain.Course;
import no.niths.domain.Student;

public interface StudentController extends GenericRESTController<Student> {

	public List<Student> getStudentsWithNamedCourse(Course course);
}

package no.niths.application.rest.interfaces;

import java.util.List;

import no.niths.domain.Course;
import no.niths.domain.Subject;

public interface CourseController extends GenericRESTController<Course> {

	public List<Subject> getCourseSubjects(Long id);

	public void addSubjectToCourse(Long courseId, Long subjectId);

	public void notUniqueObject();
}

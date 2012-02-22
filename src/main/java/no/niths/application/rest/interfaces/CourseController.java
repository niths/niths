package no.niths.application.rest.interfaces;

import java.util.List;

import no.niths.domain.Course;
import no.niths.domain.Subject;

public interface CourseController extends GenericRESTController<Course> {

	List<Subject> getCourseSubjects(Long id);
	public Course getCourse(String name,
			Integer grade, String term);
	
	public void addSubjectToCourse(Long courseId,Long subjectId);
	public void notUniqueObject() ;
}

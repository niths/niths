package no.niths.services.interfaces;

import no.niths.domain.Course;

public interface CourseService extends GenericService<Course>{

	public Course getCourse(String name, Integer grade, String term);

}

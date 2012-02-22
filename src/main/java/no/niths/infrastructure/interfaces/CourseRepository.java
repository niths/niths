package no.niths.infrastructure.interfaces;

import no.niths.domain.Course;



public interface CourseRepository  extends GenericRepository<Course> {

	Course getCourse(String name, int grade, String term);
  
}
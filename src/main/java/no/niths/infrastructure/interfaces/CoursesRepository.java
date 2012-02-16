package no.niths.infrastructure.interfaces;

import no.niths.domain.Course;



public interface CoursesRepository  extends GenericRepository<Course> {

	Course getCourse(String name, int grade, String term);
  
}
package no.niths.infrastructure.interfaces;

import no.niths.domain.Course;



public interface CoursesRepository  extends GenericRepository<Course> {

	Course getAll(String name, int grade, String term);
  
}
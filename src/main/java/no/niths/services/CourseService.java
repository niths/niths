package no.niths.services;

import no.niths.domain.Course;

public interface CourseService {
	
	Course getCourseById(long id);	
	Course getCourseByName(String name);
	

}

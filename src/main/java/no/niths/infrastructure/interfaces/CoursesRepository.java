package no.niths.infrastructure.interfaces;

import java.util.List;

import no.niths.domain.Course;


public interface CoursesRepository   {

	Long create(Course course);
	
	//Read
	List<Course> getAll();
	
	Course getByCourseId(long cid);
	
	Course getByCourseName(String name);
	
	void update(Course course);
	
	Course delete(Course course);

}

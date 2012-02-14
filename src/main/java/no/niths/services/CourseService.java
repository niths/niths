package no.niths.services;

import java.util.List;

import no.niths.domain.Course;
import no.niths.infrastructure.interfaces.CoursesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CoursesRepository repo;

    public void createCourse(Course course) {
        repo.createCourse(course);
    }

    public Course getCourseById(long id) {
    	Course c = repo.getCourseById(id);
    	if(c != null){
    		c.getTopics().size();
    	}
        return c;
   }
    
    public List<Course> getAllCourses(Course c) {
    	List<Course> results = repo.getAllCourses(c);
    	for (Course cor : results){
    		cor.getTopics().size();
    	}
        return results;
    }

    public void updateCourse(Course course) {
        repo.updateCourse(course);
    }

    public void deleteCourse(long id) {
        repo.deleteCourse(id);
    }
}
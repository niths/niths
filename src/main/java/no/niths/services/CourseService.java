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
    private CoursesRepository<Course> repo;

    public void createCourse(Course course) {
        repo.create(course);
    }

    public Course getCourseById(long id) {
    	Course c = repo.getById(id);
    	if(c != null){
    		c.getTopics().size();
    	}
        return c;
   }
    
    public List<Course> getAllCourses(Course c) {
    	List<Course> results = repo.getAll(c);
    	for (Course cor : results){
    		cor.getTopics().size();
    	}
        return results;
    }

    public void updateCourse(Course course) {
        repo.update(course);
    }

    public void deleteCourse(long id) {
        repo.delete(id);
    }
}
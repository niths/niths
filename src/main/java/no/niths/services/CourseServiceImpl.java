package no.niths.services;

import java.util.List;

import no.niths.domain.Course;
import no.niths.infrastructure.interfaces.CourseRepository;
import no.niths.services.interfaces.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository repo;

    public Long create(Course course) {
        return repo.create(course);
    }

    public Course getById(long id) {
    	Course c = repo.getById(id);
    	if(c != null){
    		c.getSubjects().size();
    	}
        return c;
   }
    
    public List<Course> getAll(Course c) {
    	List<Course> results = repo.getAll(c);
    	for (Course cor : results){
    		cor.getSubjects().size();
    	}
        return results;
    }

    public void update(Course course) {
        repo.update(course);
    }

    public boolean delete(long id) {
        return repo.delete(id);
    }

}
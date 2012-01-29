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
    CoursesRepository dao;
   
   
    public Course getCourseById(long cid){
	    return dao.getByCourseId(cid);
   }

   
    public Course getCourseByName(String name) {
        return dao.getByCourseName(name);
    }

    public List<Course> getAllCourse(){
    	return dao.getAll();
    }


	public Long create(Course course) {
		// TODO Auto-generated method stub
		return null;
	}


	public void update(Course course) {
		// TODO Auto-generated method stub
		
	}


	public Course delete(Course course) {
		// TODO Auto-generated method stub
		return null;
	}
}

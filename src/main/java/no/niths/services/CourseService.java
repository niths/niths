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

    public Course getCourseById(long cid){
        return repo.getByCourseId(cid);
   }

    public Course getCourseByName(String name) {
        return repo.getByCourseName(name);
    }

    public List<Course> getAllCourses(){
        return repo.getAll();
    }
    
    public Long create(Course course) {
        return null;
    }

    public void update(Course course) {
    }


    public Course delete(Course course) {
        return null;
    }

    public List<Course> getAll() {
        return null;
    }


    public Course getByCourseId(long cid) {
        // TODO Auto-generated method stub
        return null;
    }


    public Course getByCourseName(String name) {
        // TODO Auto-generated method stub
        return null;
    }
}
package no.niths.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import no.niths.aop.ApiEvent;
import no.niths.domain.Course;
import no.niths.infrastructure.interfaces.CourseRepository;
import no.niths.services.interfaces.CourseService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService{

	private Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	private CustomBeanUtilsBean beanCopy = new CustomBeanUtilsBean();

	
    @Autowired
    private CourseRepository repo;

    @ApiEvent(title = "Course created")
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
    	return repo.getAll(c);
    }

    @ApiEvent(title = "Course updated")
    public void update(Course course) {
    	Course courseToUpdate = repo.getById(course.getId());
    	try {
			beanCopy.copyProperties(courseToUpdate, course);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error",e);
			e.printStackTrace();
		}
		repo.update(courseToUpdate);
    }

    public boolean delete(long id) {
        return repo.delete(id);
    }

	@Override
	public void hibernateDelete(long id) {
		repo.hibernateDelete(id);
		
	}

}
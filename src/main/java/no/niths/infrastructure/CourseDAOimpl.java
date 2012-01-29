package no.niths.infrastructure;

import java.io.Serializable;

import no.niths.domain.Course;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDAOimpl implements Serializable{

    @Autowired
    SessionFactory session;

    public Course getCourseById(long id){
        return (Course) session.getCurrentSession().get(Course.class, id);
    }

    public Course getCourseByName(String name) {
        // TODO
        // Fetch by name
        return null;
    }
}
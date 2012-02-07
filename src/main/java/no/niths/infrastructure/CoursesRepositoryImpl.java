package no.niths.infrastructure;

import java.util.List;

import no.niths.domain.Course;
import no.niths.infrastructure.interfaces.CoursesRepository;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CoursesRepositoryImpl implements CoursesRepository {

    @Autowired
    private SessionFactory session;

    @Override
    @Transactional(readOnly = false)
    public Long createCourse(Course course) {
        return (Long) session.getCurrentSession().save(course);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Course> getAllCourses() {
        return session.getCurrentSession()
                .createQuery("FROM " + Course.class.getName()).list();
    }

    /**
     * Find and returns all courses which has values equal to
     * the course sent as parameter. 
     * 
     * Ex: Parameter course has name = "Programmering", will
     * return all courses found with name = "Programmering"
     * 
     * @param Course - The Course that has the values to search for
     * @return List of courses found
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Course> getAllCourses(Course c){
    	return session.getCurrentSession()
    			.createCriteria(Course.class).add(Example.create(c)).list();
    }

    @Override
    public Course getCourseById(long id) {
        return (Course) session.getCurrentSession().get(Course.class, id);
    }

    @Override
    public Course getCourseByName(String name) {
        return (Course) session
                .getCurrentSession()
                .createQuery(
                        "FROM " + Course.class.getName()
                        + " c WHERE name=:name")
                        .setString("name", name).uniqueResult();
    }
    

    @Override
    @Transactional(readOnly = false)
    public void updateCourse(Course course) {
        session.getCurrentSession().update(course);
    }

    @Override
    public boolean deleteCourse(long id) {
        Query query = session.getCurrentSession().createQuery(
        		"delete " + Course.class.getSimpleName() + " where id = :id");
        query.setParameter("id", id);
        return (1 == query.executeUpdate());
    }
}
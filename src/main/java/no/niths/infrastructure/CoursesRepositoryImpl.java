package no.niths.infrastructure;

import java.util.List;

import no.niths.domain.Course;
import no.niths.infrastructure.interfaces.CoursesRepository;

import org.hibernate.SessionFactory;
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
    @Transactional(readOnly = false)
    public void deleteCourse(Course course) {
        session.getCurrentSession().delete(course);
    }
}
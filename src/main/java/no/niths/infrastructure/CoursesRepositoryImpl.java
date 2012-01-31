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

	@Transactional(readOnly = false)
	public Long create(Course domain) {
		Long id = (Long) session.getCurrentSession().save(domain);
		return id;
	}

	@SuppressWarnings("unchecked")
	public List<Course> getAll() {
		return session.getCurrentSession()
				.createQuery("from " + Course.class.getName()).list();
	}

	public Course getByCourseId(long courseId) {
		return (Course) session.getCurrentSession().get(Course.class, courseId);
	}

	@Transactional(readOnly = false)
	public void update(Course domain) {
		session.getCurrentSession().update(domain);
	}

	@Transactional(readOnly = false)
	public Course delete(Course domain) {
		session.getCurrentSession().delete(domain);
		return domain;
	}

	@Override
	public Course getByCourseName(String name) {

		return (Course) session
				.getCurrentSession()
				.createQuery(
						"from " + Course.class.getName()
								+ " c where name=:name")
				.setString("name", name).uniqueResult();
	}
}
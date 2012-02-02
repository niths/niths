package no.niths.infrastructure;

import java.util.List;

import no.niths.domain.Student;
import no.niths.infrastructure.interfaces.StudentRepository;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
	
	private static final Logger logger = LoggerFactory
			.getLogger(StudentRepositoryImpl.class);

	@Autowired
	private SessionFactory session;

	@Transactional(readOnly = false)
	public Long create(Student domain) {
		Long id = (Long) session.getCurrentSession().save(domain);
		return id;
	}

	@SuppressWarnings("unchecked")
	public List<Student> getAllStudents() {
		return session.getCurrentSession().createQuery("from " + Student.class.getName())
				.list();
	}

	/**
	 * Returns all students that has a name equals or like the search query
	 * 
	 * @param name
	 *            the search query
	 * @return List with students
	 */
	@SuppressWarnings("unchecked")
	public List<Student> getByName(String name) {

		return session.getCurrentSession()
				.createQuery(
						"from " + Student.class.getName()
								+ " student where student.firstName like :name")
				.setString("name", "%" + name + "%").list();
	}

	/**
	 * 
	 */
	public Student getByIdWithCourseAndCommittee(long sid) {
		String sql = "from "
				+ Student.class.getSimpleName()
				+ " s join fetch s.committees c join fetch s.courses where s.id =:sid";
		return (Student) session.getCurrentSession().createQuery(sql).setParameter("sid", sid)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.uniqueResult();
	}

	/**
	 * 
	 */
	@Transactional(readOnly = false)
	public void update(Student domain) {
		session.getCurrentSession().update(domain);
	}

	@Transactional(readOnly = false)
	public Student delete(Student domain) {
		session.getCurrentSession().delete(domain);
		return domain;
	}
	

	@Transactional(readOnly = false)
	public boolean delete(long id) {
		
        Query query = session.getCurrentSession().createQuery(
        		"delete " + Student.class.getSimpleName() + " where id = :id");
        query.setParameter("id", id);
        
        return (1 == query.executeUpdate());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getStudentsWithCommittees() {
		String sql = "from " + Student.class.getSimpleName()
				+ " s join fetch s.committees c";
		return session.getCurrentSession().createQuery(sql)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getByNamedCommittee(String committyName) {
		String sql = "from " + Student.class.getName() +" s join fetch s.committees c where c=:cName";
		return session.getCurrentSession().createQuery(sql).setString("cName", committyName).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getStudentsWithCourses() {
		String sql = "from " + Student.class.getSimpleName()
				+ " s join fetch s.courses c";
		return session.getCurrentSession().createQuery(sql)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getByStudentsNamedCourse(String courseName) {
		String sql = "from " + Student.class.getSimpleName()
				+ " s join fetch s.courses c where c.name =:name";
		return session.getCurrentSession().createQuery(sql).setParameter("name", courseName)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@Override
	public List<Student> getStudentsWihtCoursesAndCommittees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getById(long sid) {

		return (Student) session.getCurrentSession().get(Student.class, sid);
	}

}

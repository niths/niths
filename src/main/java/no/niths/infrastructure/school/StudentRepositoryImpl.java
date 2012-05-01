package no.niths.infrastructure.school;

import java.util.List;

import no.niths.domain.school.Student;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.QueryGenerator;
import no.niths.infrastructure.school.interfaces.StudentRepository;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

/**
 * Repository class for Student
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for getStudentsWithNamedCourse
 * and getStudentByColumn
 * </p>
 */
@Repository
public class StudentRepositoryImpl extends AbstractGenericRepositoryImpl<Student>
		implements StudentRepository {

	
	private QueryGenerator<Student> queryGen;
	
	public StudentRepositoryImpl() {
		super(Student.class, new Student());
		queryGen = new QueryGenerator<Student>(Student.class);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Student> getStudentsWithNamedCourse(String name) {
		String sql = "from " + Student.class.getSimpleName()
				+ " s join fetch s.courses c where c.name=:name";
		return getSession().getCurrentSession().createQuery(sql)
				.setString("name", name)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public List<Student> getStudentByColumn(String column, String criteria) {
		
		return queryGen.whereQuery(criteria, column, getSession().getCurrentSession());
	}

}

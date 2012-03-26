package no.niths.infrastructure;

import java.util.List;

import no.niths.domain.Student;
import no.niths.infrastructure.interfaces.StudentRepository;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepositoryImpl extends AbstractGenericRepositoryImpl<Student>
		implements StudentRepository {

	
	private QueryGenerator<Student> queryGen;
	
	public StudentRepositoryImpl() {
		super(Student.class, new Student());
		queryGen = new QueryGenerator<Student>(Student.class);
	}

	@SuppressWarnings("unchecked")
	public List<Student> getStudentsWithNamedCourse(String name) {
		String sql = "from " + Student.class.getSimpleName()
				+ " s join fetch s.courses c where c.name=:name";
		return getSession().getCurrentSession().createQuery(sql)
				.setString("name", name)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}
	
	@Override
	public List<Student> getStudentByColumn(String column, String criteria) {
		
		return queryGen.whereQuery(criteria, column, getSession().getCurrentSession());
	}

}

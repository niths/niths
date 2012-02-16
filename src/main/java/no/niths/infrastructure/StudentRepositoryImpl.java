package no.niths.infrastructure;

import java.util.List;

import no.niths.domain.Student;
import no.niths.infrastructure.interfaces.StudentRepository;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepositoryImpl extends GenericRepositoryImpl<Student>
		implements StudentRepository {

	public StudentRepositoryImpl() {
		super(Student.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Student> getStudentsWithNamedCourse(String name) {
		String sql = "from " + Student.class.getSimpleName()
				+ " s join fetch s.courses c where c.name=:name";
		return getSession().getCurrentSession().createQuery(sql).setString("name", name)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}
}

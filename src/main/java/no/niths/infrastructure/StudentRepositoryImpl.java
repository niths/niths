package no.niths.infrastructure;

import java.util.List;

import no.niths.domain.Student;
import no.niths.domain.security.Role;
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
	
	public Student getStudentByEmail(String email){
		String sql = "from " + Student.class.getSimpleName()
				+ " s where s.email=:email";
		return (Student) getSession().getCurrentSession()
				.createQuery(sql)
				.setString("email", email)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getAllStudentsInAFadderUka() {
		String sql = "from " + Student.class.getSimpleName()
				+ " s join fetch s.fadderUka";
		return getSession().getCurrentSession().createQuery(sql)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getAllStudentsInFadderUkaBelongingToAGroup(int groupId) {
		String sql = "from " + Student.class.getSimpleName()
				+ " s join fetch s.fadderUka m where m.groupId=:gid";
		return getSession().getCurrentSession().createQuery(sql).setInteger("gid", groupId)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}
	
	@Override
	public void hibernateDelete(long id) {
		Student s = new Student();
		s.setId(id);
		
		getSession().getCurrentSession().delete(s);
	}
}

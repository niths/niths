package no.niths.infrastructure.school;

import java.util.List;

import no.niths.common.misc.Searchable;
import no.niths.domain.school.Student;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.QueryGenerator;
import no.niths.infrastructure.school.interfaces.StudentRepository;

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
	
	/**
	 * Returns all student in the given course
	 * 
	 * @param name The course name
	 * @return list of students in the course
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
	 * Returns all students with matching attribute
	 * Columns must be annotated with @Searchable
	 * 
	 * @see Searchable
	 * 
	 * @param column the attribute to search for
	 * @param criteria the search query
	 * @return List of matching students
	 * 
	 */
	@Override
	public List<Student> getStudentByColumn(String column, String criteria) {
		
		return queryGen.whereQuery(criteria, column, getSession().getCurrentSession());
	}

}

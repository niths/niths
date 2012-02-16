package no.niths.infrastructure;

import java.util.List;

import no.niths.domain.Course;
import no.niths.domain.Student;
import no.niths.infrastructure.interfaces.CoursesRepository;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

@Repository
public class CoursesRepositoryImpl extends GenericRepositoryImpl<Course>
		implements CoursesRepository {

	public CoursesRepositoryImpl() {
		super(Course.class);
	}

	public Course getAll(String name, int grade, String term) {
		String sql = "from " + Course.class.getSimpleName() + " c " +
				"where c.name=:name " +
				"and c.grade=:grade " +
				"and c.term=:term";
		return (Course) getSession().getCurrentSession().createQuery(sql)
				.setString("name", name)
				.setString("term", term)
				.setInteger("grade", grade)
				.uniqueResult();
	}

}
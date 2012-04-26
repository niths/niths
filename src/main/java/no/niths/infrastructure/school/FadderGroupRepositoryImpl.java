package no.niths.infrastructure.school;

import java.util.List;

import no.niths.domain.school.FadderGroup;
import no.niths.domain.school.Student;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.school.interfaces.FadderGroupRepository;

import org.springframework.stereotype.Repository;
/**
 * Repository class for FadderGroup
 *
 */
@Repository
public class FadderGroupRepositoryImpl extends
		AbstractGenericRepositoryImpl<FadderGroup> implements
		FadderGroupRepository {

	public FadderGroupRepositoryImpl() {
		super(FadderGroup.class, new FadderGroup());
	}
	
	/**
     * Returns all students without a fadder group
     * 
     * @return list with students
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getStudentsNotInAGroup() {
		String sql = "from " + Student.class.getSimpleName() + " s "
				+ "where s.fadderGroup = null";
		
		return getSession().getCurrentSession().createQuery(sql).list();
	}
	

	/**
	 * Returns the group the student is a child in
	 * 
	 * @param studentId id of the student
	 * @return FadderGroup the group the student is a child in
	 */
	@Override
	public FadderGroup getGroupBelongingToStudent(Long studentId) {
		String sql = "from " + FadderGroup.class.getSimpleName() + " f "
				+ "join fetch f.fadderChildren c where c.id = :studentId";
		
		return (FadderGroup) getSession().getCurrentSession().createQuery(sql)
				.setLong("studentId", studentId).uniqueResult();
	}

}

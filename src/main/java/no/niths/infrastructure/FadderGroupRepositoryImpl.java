package no.niths.infrastructure;

import no.niths.domain.FadderGroup;
import no.niths.infrastructure.interfaces.FadderGroupRepository;

import org.springframework.stereotype.Repository;

@Repository
public class FadderGroupRepositoryImpl extends
		AbstractGenericRepositoryImpl<FadderGroup> implements
		FadderGroupRepository {

	public FadderGroupRepositoryImpl() {
		super(FadderGroup.class, new FadderGroup());
	}

	@Override
	public FadderGroup getGroupBelongingToStudent(Long studentId) {
		String sql = "from " + FadderGroup.class.getSimpleName() + " f "
				+ "join fetch f.fadderChildren c where c.id = :studentId";
		
		return (FadderGroup) getSession().getCurrentSession().createQuery(sql)
				.setLong("studentId", studentId).uniqueResult();
	}

}

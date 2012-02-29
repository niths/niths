package no.niths.infrastructure;

import no.niths.domain.FadderGroup;
import org.springframework.stereotype.Repository;

import no.niths.infrastructure.interfaces.FadderGroupRepository;

@Repository
public class FadderGroupRepositoryImpl extends GenericRepositoryImpl<FadderGroup> implements FadderGroupRepository {

	public FadderGroupRepositoryImpl() {
		super(FadderGroup.class);
	}

	@Override
	public void hibernateDelete(long id) {
		FadderGroup f = new FadderGroup();
		f.setId(id);
		getSession().getCurrentSession().delete(f);
	}
}

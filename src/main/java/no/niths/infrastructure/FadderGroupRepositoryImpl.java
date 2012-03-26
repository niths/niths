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

}

package no.niths.infrastructure;

import no.niths.domain.Committee;
import no.niths.infrastructure.interfaces.CommitteeRepositorty;

import org.springframework.stereotype.Repository;

@Repository
public class CommitteeRepositoryImpl extends GenericRepositoryImpl<Committee>
		implements CommitteeRepositorty{

	public CommitteeRepositoryImpl() {
		super(Committee.class);
	}

}

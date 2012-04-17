package no.niths.infrastructure.school;

import no.niths.domain.school.Committee;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.school.interfaces.CommitteeRepositorty;

import org.springframework.stereotype.Repository;

@Repository
public class CommitteeRepositoryImpl extends AbstractGenericRepositoryImpl<Committee>
		implements CommitteeRepositorty{

	public CommitteeRepositoryImpl() {
		super(Committee.class, new Committee());
	}
	
}

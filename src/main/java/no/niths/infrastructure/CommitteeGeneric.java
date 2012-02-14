package no.niths.infrastructure;

import org.springframework.stereotype.Repository;

import no.niths.domain.Committee;
import no.niths.infrastructure.interfaces.GenericRepository;
@Repository
public class CommitteeGeneric extends GenericRepositoryImpl<Committee> implements GenericRepository<Committee>{

	public CommitteeGeneric() {
		super(Committee.class);
	}


}

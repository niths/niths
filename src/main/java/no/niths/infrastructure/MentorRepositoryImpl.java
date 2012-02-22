package no.niths.infrastructure;

import org.springframework.stereotype.Repository;

import no.niths.domain.Mentor;
import no.niths.infrastructure.interfaces.MentorRepository;

@Repository
public class MentorRepositoryImpl extends GenericRepositoryImpl<Mentor> implements MentorRepository{

	public MentorRepositoryImpl() {
		super(Mentor.class);
	}

}

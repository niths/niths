package no.niths.infrastructure;

import org.springframework.stereotype.Repository;

import no.niths.domain.StudentOrientationGroup;
import no.niths.infrastructure.interfaces.MentorRepository;

@Repository
public class MentorRepositoryImpl extends GenericRepositoryImpl<StudentOrientationGroup> implements MentorRepository{

	public MentorRepositoryImpl() {
		super(StudentOrientationGroup.class);
	}

}

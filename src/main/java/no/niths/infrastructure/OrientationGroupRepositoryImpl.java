package no.niths.infrastructure;

import org.springframework.stereotype.Repository;

import no.niths.domain.StudentOrientationGroup;
import no.niths.infrastructure.interfaces.OrientationGroupRepository;

@Repository
public class OrientationGroupRepositoryImpl extends GenericRepositoryImpl<StudentOrientationGroup> implements OrientationGroupRepository{

	public OrientationGroupRepositoryImpl() {
		super(StudentOrientationGroup.class);
	}

}

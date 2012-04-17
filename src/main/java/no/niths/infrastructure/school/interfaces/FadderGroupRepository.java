package no.niths.infrastructure.school.interfaces;

import no.niths.domain.school.FadderGroup;
import no.niths.infrastructure.interfaces.GenericRepository;


public interface FadderGroupRepository extends GenericRepository<FadderGroup>{

	FadderGroup getGroupBelongingToStudent(Long studentId);
	
}

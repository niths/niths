package no.niths.infrastructure.interfaces;

import no.niths.domain.school.FadderGroup;


public interface FadderGroupRepository extends GenericRepository<FadderGroup>{

	FadderGroup getGroupBelongingToStudent(Long studentId);
	
}

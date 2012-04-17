package no.niths.services.school.interfaces;

import no.niths.domain.school.FadderGroup;
import no.niths.services.interfaces.GenericService;

public interface FadderGroupService extends GenericService<FadderGroup> {

	FadderGroup getGroupBelongingToStudent(Long studentId);
	
}
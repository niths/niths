package no.niths.application.rest.interfaces;

import no.niths.domain.FadderGroup;

public interface FadderGroupController extends GenericRESTController<FadderGroup> {
	
	public void addLeaderToAGroup(Long groupId, Long studId);
	
	public void removeLeaderFromAGroup(Long groupId, Long studId);

}

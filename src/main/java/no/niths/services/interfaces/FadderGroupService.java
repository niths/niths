package no.niths.services.interfaces;

import no.niths.domain.FadderGroup;
import no.niths.domain.Student;

public interface FadderGroupService extends GenericService<FadderGroup> {
	/**
	 * Remove a children from a group
	 * 
	 * @param stud the children to remove
	 * @param group the group to remove children from
	 */
	public void removeAChildrenFromAGroup(Student student, FadderGroup group);
	/**
	 * Remove a leader from a group
	 * 
	 * @param stud the leader to remove
	 * @param group the group to remove leader from
	 */
	public void removeALeaderFromAGroup(Student student, FadderGroup group);
	
	/**
	 * Removes all children from a fadder group
	 * 
	 * @param group the group to remove all children from
	 */
	public void removeAllChildrenFromGroup(FadderGroup group);
	
	/**
	 * Remove all leaders from a group
	 * 
	 * @param group the group to remove all leaders from
	 */
	public void removeAllLeadersFromGroup(FadderGroup group);
	
	/**
	 * Adds a leader to a group
	 * 
	 * @param leader the student to add as a leader
	 * @param group the group to add a leader to
	 */
	public void addLeaderToGroup(Student leader, FadderGroup group);
	
	/**
	 * Adds a child to a group
	 * 
	 * @param child the student to add as a child
	 * @param group the group to add a child to
	 */
	public void addChildrenToGroup(Student child, FadderGroup group);

}

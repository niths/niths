package no.niths.application.rest.school.interfaces;

import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.school.Committee;

/**
 * Controller for committees
 */
public interface CommitteeController extends GenericRESTController<Committee> {

	/**
	 * Adds a leader to a committee.
	 * 
	 * @param committeeId
	 *            the id of the committee
	 * @param studentId
	 *            the id of the student
	 */
	public void addLeader(Long committeeId, Long studentId);

	/**
	 * Removes a leader from a committee
	 * 
	 * @param committeeId
	 *            the id of the committee
	 * @param studentId
	 *            the id of the student
	 */
	public void removeLeader(Long committeeId, Long studentId);
	
	/**
	 * Ads an event to a committee
	 * 
	 * @param committeeId id of the committee
	 * @param eventId if of the event
	 */
	public void addEvent(Long committeeId, Long eventId);
	
	/**
	 * Removes an event from a committee
	 * 
	 * @param committeeId id of the committee
	 * @param eventId if of the event
	 */
	public void removeEvent(Long committeeId, Long eventId);

}
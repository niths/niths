package no.niths.application.rest.interfaces;

import no.niths.domain.Committee;

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

	/**
	 * Catches constraint violation exceptions Ex: Leader already added to
	 * committee
	 */
	public void notUniqueObject();
}

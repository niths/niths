package no.niths.application.rest.school.interfaces;

import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.school.Committee;

/**
 * Controller for committee
 * has the basic CRUD methods and
 * methods too add and remove leader
 * and event
 *
 * For the URL too get Committee add /committees
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
public interface CommitteeController extends GenericRESTController<Committee> {

	/**
	 * Adds a leader to a committee.
     *
     * Too add leader add /{committeeId}/leader/{studentId}
     * too the URL
     *
     * Use the POST method
	 * 
	 * @param committeeId the id of the committee
	 * @param studentId the id of the student
	 */
	public void addLeader(Long committeeId, Long studentId);

	/**
	 * Removes a leader from a committee
     *
     * Too remove leader add /{committeeId}/leader/{studentId}
     * too the URL
     *
     * Use the DELETE method
	 * 
	 * @param committeeId the id of the committee
	 * @param studentId the id of the student
	 */
	public void removeLeader(Long committeeId, Long studentId);
	
	/**
	 * Ads an event to a committee
     *
     * Too add event add /{committeeId}/event/{eventId}
     * too the URL
     *
     * Use the POST method
	 * 
	 * @param committeeId id of the committee
	 * @param eventId if of the event
	 */
	public void addEvent(Long committeeId, Long eventId);
	
	/**
	 * Removes an event from a committee
     *
     * Too remove event add /{committeeId}/event/{eventId}
     * too the URL
     *
     * Use the DELETE method
	 * 
	 * @param committeeId id of the committee
	 * @param eventId if of the event
	 */
	public void removeEvent(Long committeeId, Long eventId);
}

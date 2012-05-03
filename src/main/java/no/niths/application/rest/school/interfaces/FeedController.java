package no.niths.application.rest.school.interfaces;

import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.school.Feed;

/**
 * Controller for feed
 * has the basic CRUD methods and
 * methods too add and remove location,
 * student and committee
 *
 * For the URL too get Feeds add /feeds
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 *
 * <pre>
 * <h1>This is a feed</h1>
 * <p>
 * The purpose of the feed is to be a Twitter like feed
 * so students can post interesting messages, or something thats happening
 * right now. like <quote>Sale on computer software on komplett.no</quote>
 * </p>
 * </pre>
 */
public interface FeedController extends GenericRESTController<Feed> {

	/**
	 * Method for adding location
     *
     * Too add a location add /locations
     * too the URL
     *
     * Use the POST method
     *
	 * @param feedId id for feed
	 * @param locationId id for location
	 */
	void addLocation(Long feedId, Long locationId);

	/**
	 * Removing a location from a feed
     *
     * Too remove a location add /locations
     * too the URL
     *
     * Use the DELETE method
     *
	 * @param feedId id for the feed
	 */
	void removeLocation(Long feedId);

	/**
	 * Adding a student to a feed
     *
     * Too add a student add /students
     * too the URL
     *
     * Use the POST method
     *
	 * @param feedId id for feed
	 * @param studentId id for student
	 */
	void addStudent(Long feedId, Long studentId);

	/**
	 * Removing a student from a feed
     *
     * Too remove a student add /students
     * too the URL
     *
     * Use the DELETE method
     *
	 * @param feedId id for the feed
	 */
	void removeStudent(Long feedId);
	
	/**
	 * Adding a committee relationship to the feed with the provided id
     *
     * Too add a committee add /committees
     * too the URL
     *
     * Use the POST method
     *
	 * @param feedId id for feed
	 * @param committeeId id for committee
	 */
	void addCommittee(Long feedId, Long committeeId);
	
	/**
	 * Removing committee from a feed
     *
     * Too remove a committee add /committees
     * too the URL
     *
     * Use the DELETE method
     *
	 * @param feedId id for feed
	 */
	void removeCommittee(Long feedId);
	
}

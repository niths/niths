package no.niths.application.rest.school.interfaces;

import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.school.Feed;

/**
 * <pre>
 * <h1>This is a feed</h1>
 * <p>
 * The purpose of the feed is to be a Twitter like feed
 * so students can post interesting messages, or something thats happening 
 * right now. like <quote>Sale on computer software on komplett.no</quote>  
 * </p>
 * </pre>
 *
 */
public interface FeedController extends GenericRESTController<Feed> {

	/**
	 * Method for adding location
	 * @param feedId
	 * @param locId
	 */
	void addLocation(Long feedId, Long locationId);

	/**
	 * Removing a location from a feed
	 * @param feedId
	 */
	void removeLocation(Long feedId);

	/**
	 * Adding a student to a feed
	 * @param feedId
	 * @param studentId
	 */
	void addStudent(Long feedId, Long studentId);

	/**
	 * Removing a student from a feed
	 * @param feedId
	 */
	void removeStudent(Long feedId);
	
	/**
	 * Adding a committee relationship to the feed with the provided id
	 * @param feedId
	 * @param committeeId
	 */
	void addCommittee(Long feedId, Long committeeId);
	
	/**
	 * Removing committee from a feed
	 * @param feedId
	 */
	void removeCommittee(Long feedId);
	
}

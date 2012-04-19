package no.niths.application.rest.school.interfaces;

import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.school.Feed;

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
}

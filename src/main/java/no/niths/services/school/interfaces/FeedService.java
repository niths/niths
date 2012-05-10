package no.niths.services.school.interfaces;

import no.niths.domain.school.Feed;
import no.niths.services.interfaces.GenericService;
/**
 * Service Class for Feed
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for addLocation, removeLocation,
 * addStudent, removeStudent,
 * addCommittee
 * and removeCommittee
 * </p>
 */
public interface FeedService extends GenericService<Feed>{

    /**
     * Adds a location to the feed
     * @param feedId id for feed
     * @param locationId id for location
     */
    void addLocation(Long feedId, Long locationId);

    /**
     * Removes a location from a feed
     * @param feedId id for feed
     */
    void removeLocation(Long feedId);

    /**
     * Adds a student to the feed
     * @param feedId id for feed
     * @param studentId id for student
     */
    void addStudent(Long feedId, Long studentId);

    /**
     * Removes a student from the feed
     * @param feedId id for feed
     */
    void removeStudent(Long feedId);

    /**
     * Removes a committee from the feed
     * @param feedId id for feed
     */
    void removeCommittee(Long feedId);

    /**
     * Adds a committee to the feed
     * @param feedId id for feed
     * @param committeeId id for committee
     */
    void addCommittee(Long feedId, Long committeeId);

}

package no.niths.services.school.interfaces;

import no.niths.domain.school.Committee;
import no.niths.services.interfaces.GenericService;
/**
 * Service Class for Committee
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for addLeader, removeLeader,
 * addEvent and removeEvent
 * </p>
 */
public interface CommitteeService extends GenericService<Committee> {

    /**
     * Adds a leader (Student) to the committee
     * @param committeeId id for committee
     * @param studentId id for student
     */
    void addLeader(Long committeeId, Long studentId);

    /**
     * Removes a leader (Student) from a committee
     * @param committeeId id for committee
     * @param studentId id for student
     */
    void removeLeader(Long committeeId, Long studentId);

    /**
     * Adds a event to the committee
     * @param committeeId id for committee
     * @param eventId id for event
     */
    void addEvent(Long committeeId, Long eventId);

    /**
     * Removes a event from a committee
     * @param committeeId id for committee
     * @param eventId id for event
     */
    void removeEvent(Long committeeId, Long eventId);
}
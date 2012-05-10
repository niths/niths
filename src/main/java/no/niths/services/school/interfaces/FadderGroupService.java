package no.niths.services.school.interfaces;

import java.util.List;

import no.niths.domain.school.FadderGroup;
import no.niths.domain.school.Student;
import no.niths.services.interfaces.GenericService;
/**
 * Service Class for FadderGroup
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for addLeader, removeLeader,
 * removeAllLeaders, addChild,
 * removeChild, removeChildren,
 * addChildren, removeAllChildren
 * and getStudentsNotInAGroup
 * </p>
 */
public interface FadderGroupService extends GenericService<FadderGroup> {

    /**
     * Adds a leader (Student) to the fadderGroup
     * @param groupId id for fadderGroup
     * @param studentId id for leader
     */
    void addLeader(Long groupId, Long studentId);

    /**
     * Removes a leader (Student) from a fadderGroup
     * @param groupId id for fadderGroup
     * @param studentId id for student
     */
    void removeLeader(Long groupId, Long studentId);

    /**
     * Removes all leaders from a fadderGroup
     * @param groupId id for fadderGroup
     */
    void removeAllLeaders(Long groupId);

    /**
     * Adds a child (Student) to the fadderGroup
     * @param groupId id for fadderGroup
     * @param studentId id for student
     */
    void addChild(Long groupId, Long studentId);

    /**
     * Removes a child (Student) from a fadderGroup
     * @param groupId id for fadderGroup
     * @param studentId id for student
     */
    void removeChild(Long groupId, Long studentId);

    /**
     * Removes a list of children (Students) from a fadderGroup
     * @param groupId id for fadderGroup
     * @param studentIds list of ids for students
     */
    void removeChildren(Long groupId, Long [] studentIds);

    /**
     * Removes all children from a fadderGroup
     * @param groupId id for fadderGroup
     */
    void removeAllChildren(Long groupId);

    /**
     * Returns all students without a fadder group
     * @return list with students
     */
    List<Student> getStudentsNotInAGroup();

    /**
     * Adds a list of children to the fadderGroup
     * @param groupId id for fadderGroup
     * @param studentIds list of ids for students
     */
    void addChildren(Long groupId, Long[] studentIds);
}
package no.niths.application.rest.school.interfaces;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.exception.QRCodeException;
import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.school.FadderGroup;
import no.niths.domain.school.Student;

/**
 * 
 * @author NITHs
 *
 */
public interface FadderGroupController extends GenericRESTController<FadderGroup> {

    /**
     * Adds a leader to a group
     * 
     * @param groupId id of the group
     * @param studentId id of the leader
     * @throws ObjectNotFoundException if student of group does not exists
     */
    public void addLeader(Long groupId, Long studentId);

    /**
     * Removes a leader from a group
     * 
     * @param groupId id of the group to remove a leader from
     * @param studentId id of the student to remove as a leader
     * @throws ObjectNotFoundException if student of group does not exists
     */
    public void removeLeader(Long groupId, Long studentId);

    /**
     * Adds a child to a group
     * 
     * @param groupId id of the group to add a child to
     * @param studentId id of the student to add
     * @throws ObjectNotFoundException if student of group does not exists
     */
    public void addChild(Long groupId, Long studentId);

    /**
     * Remove a child from a group
     * 
     * @param groupId id of the group to remove the child from
     * @param studentId if of the student to remove
     * @throws ObjectNotFoundException if student in the group does not exist
     */
    public void removeChild(Long groupId, Long studentId);

    /**
     * 
     * @param groupId id of the group to remove the children from
     * @param studentId id of the student to remove
     * @throws ObjectNotFoundException if the students in group do not exist
     */
    public void removeChildren(Long groupId, Long[] studentId);

    /**
     * Removes all children from a group
     * 
     * @param groupId id of the group to remove child from
     * @throws ObjectNotFoundException if the group does not exists
     */
    public void removeAllChildren(Long groupId);

    /**
     * Removes all leaders from a group
     * 
     * @param groupId id of the group to remove leaders from
     * @throws ObjectNotFoundException if the group does not exists
     */
    public void removeAllLeaders(Long groupId);

    void scanImage(HttpServletRequest request, HttpServletResponse response)
            throws QRCodeException;

    /**
     * Gets all the students in a given fadder group
     * @param groupId id of the FadderGroup
     * @return List of students
     */
    public List<Student> getAllStudents(Long groupId);

    /**
     * Returns all students that has no fadder group
     * @return list with students
     * @throws ObjectNotFoundException if all students has a group
     */
	List<Student> getAllStudentsNotInAGroup();

	void addChildren(Long groupId, Long[] studentIds);

}

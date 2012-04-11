package no.niths.application.rest.interfaces;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.exception.QRCodeException;
import no.niths.domain.FadderGroup;
import no.niths.domain.Student;

import com.google.zxing.WriterException;

import com.google.zxing.WriterException;

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
     * @param studId id of the leader
     * @throws ObjectNotFoundException if student of group does not exists
     */
    public void addLeaderToAGroup(Long groupId, Long studId);

    /**
     * Removes a leader from a group
     * 
     * @param groupId id of the group to remove a leader from
     * @param studId id of the student to remove as a leader
     * @throws ObjectNotFoundException if student of group does not exists
     */
    public void removeLeaderFromAGroup(Long groupId, Long studId);

    /**
     * Adds a child to a group
     * 
     * @param groupId id of the group to add a child to
     * @param studId id of the student to add
     * @throws ObjectNotFoundException if student of group does not exists
     */
    public void addChildToAGroup(Long groupId, Long studId);

    /**
     * Remove a child from a group
     * 
     * @param groupId id of the group to remove the child from
     * @param studId if of the student to remove
     * @throws ObjectNotFoundException if student in the group does not exist
     */
    public void removeChildFromGroup(Long groupId, Long studId);

    /**
     * 
     * @param groupId id of the group to remove the children from
     * @param studId id of the student to remove
     * @throws ObjectNotFoundException if the students in group do not exist
     */
    public void removeChildrenFromGroup(Long groupId, Long[] studId);

    /**
     * Removes all children from a group
     * 
     * @param groupId id of the group to remove child from
     * @throws ObjectNotFoundException if the group does not exists
     */
    public void removeAllChildrenFromGroup(Long groupId);

    /**
     * Removes all leaders from a group
     * 
     * @param groupId id of the group to remove leaders from
     * @throws ObjectNotFoundException if the group does not exists
     */
    public void removeAllLeadersFromGroup(Long groupId);

    void scanImage(HttpServletRequest request, HttpServletResponse response)
            throws QRCodeException;

    /**
     * Gets all the students in a given fadder group
     * @param The id of the FadderGroup
     * @return List of students
     */
    public List<Student> getAllStudentsFromFadderGroup(Long id);

	FadderGroup getGroupBelongingToStudent(Long studentId);
}

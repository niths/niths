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
 * Controller for FadderGroup
 * has the basic CRUD methods and
 * methods too add and remove leader, child
 * and children and remove all leaders and children
 * in addition too methods for getAllStudentsNotInAGroup,
 * getAllStudents and scanImage
 *
 * For the URL too get FadderGroup add /fadder
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
public interface FadderGroupController extends GenericRESTController<FadderGroup> {

    /**
     * Adds a leader to a group
     *
     * Too add leader add /{groupId}/leader/{studentId}
     * too the URL
     *
     * Use the POST method
     * 
     * @param groupId id of the group
     * @param studentId id of the leader
     * @throws ObjectNotFoundException if student of group does not exists
     */
    public void addLeader(Long groupId, Long studentId);

    /**
     * Removes a leader from a group
     *
     * Too remove leader add /{groupId}/leader/{studentId}
     * too the URL
     *
     * Use the DELETE method
     * 
     * @param groupId id of the group to remove a leader from
     * @param studentId id of the student to remove as a leader
     * @throws ObjectNotFoundException if student of group does not exists
     */
    public void removeLeader(Long groupId, Long studentId);

    /**
     * Adds a child to a group
     *
     * Too add child add /{groupId}/child/{studentId}
     * too the URL
     *
     * Use the POST method
     * 
     * @param groupId id of the group to add a child to
     * @param studentId id of the student to add
     * @throws ObjectNotFoundException if student of group does not exists
     */
    public void addChild(Long groupId, Long studentId);

    /**
     * Remove a child from a group
     *
     * Too remove child add /{groupId}/child/{studentId}
     * too the URL
     *
     * Use the DELETE method
     * 
     * @param groupId id of the group to remove the child from
     * @param studentId if of the student to remove
     * @throws ObjectNotFoundException if student in the group does not exist
     */
    public void removeChild(Long groupId, Long studentId);

    /**
     * Removes children whos id is in the list with student ids
     *
     * Too remove children add /{groupId}/children/{studentIds}
     * too the URL
     *
     * Use the DELETE method
     *
     * @param groupId id of the group to remove the children from
     * @param studentIds id of the students to remove
     * @throws ObjectNotFoundException if the students in group do not exist
     */
    public void removeChildren(Long groupId, Long[] studentIds);

    /**
     * Removes all children from a group
     *
     * Too remove all children add /{groupId}/children
     * too the URL
     *
     * Use the DELETE method
     * 
     * @param groupId id of the group to remove child from
     * @throws ObjectNotFoundException if the group does not exists
     */
    public void removeAllChildren(Long groupId);

    /**
     * Removes all leaders from a group
     *
     * Too remove all leader add /{groupId}/leaders
     * too the URL
     *
     * Use the DELETE method
     * 
     * @param groupId id of the group to remove leaders from
     * @throws ObjectNotFoundException if the group does not exists
     */
    public void removeAllLeaders(Long groupId);

    /**
     * Scans a provided QR code and adds a student to the group in the qr.
     * <p>
     * The qr code must contain a string on the form: 
     * gruppe:gruppeid (ex: gruppe:3)
     * </p>
     * @param studentId the student to add to the group
     * @param request 
     * @param response
     * @throws QRCodeException when QR is in wrong format or unreadble
     */
    void scanImage(Long studentId, HttpServletRequest request, HttpServletResponse response)
            throws QRCodeException;

    /**
     * Gets all the students in a given fadder group
     *
     * Too get all children in a group add /{groupId}/children
     * too the URL
     *
     * Use the GET method
     *
     * @param groupId id of the FadderGroup
     * @return List of students
     */
    public List<Student> getAllStudents(Long groupId);

    /**
     * Returns all students that has no fadder group
     *
     * Too get all children who's not in a group add /groupless
     * too the URL
     *
     * Use the GET method
     *
     * @return list with students
     * @throws ObjectNotFoundException if all students has a group
     */
    List<Student> getAllStudentsNotInAGroup();

    /**
     * Adds multiple students as children to a group
     *
     * Too add children add /{groupId}/children/{studentIds}
     * too the URL
     *
     * Use the POST method
     * 
     * @param groupId the group to add children to
     * @param studentIds array with student ids
     */
    void addChildren(Long groupId, Long[] studentIds);


}

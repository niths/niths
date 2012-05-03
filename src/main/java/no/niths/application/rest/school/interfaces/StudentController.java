package no.niths.application.rest.school.interfaces;

import java.util.List;

import no.niths.application.rest.interfaces.GenericRESTController;
import no.niths.domain.school.Course;
import no.niths.domain.school.Student;

/**
 * Controller for student
 * has the basic CRUD methods and
 * methods too add and remove course,
 * committee, feed, loan, role
 * and locker
 * in addition too methods for getStudentsWithNamedCourse,
 * getStudentWithRoles, isStudentInRole
 * and removeAllRolesFromStudent
 *
 * For the URL too get Students add /students
 * after the {@value no.niths.common.constants.MiscConstants#NITHS_BASE_DOMAIN}
 */
public interface StudentController extends GenericRESTController<Student> {

    /**
     * HOW TO USE SECURITY ANNOTATIONS:
     * 
     * One role allowed:
     * @PreAuthorize("hasRole('ROLE_ADMIN')")
     * Multiple roles allowed:
     * @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEST')")
     * 
     */

//  EXAMPLE OF HOW TO USE:    
//    @Override
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEST')")
//    public ArrayList<Student> getAll(Student domain);

    /**
     * Returns a list of all students in a given course
     *
     * Too get all courses add /course
     * too the URL
     *
     * Use the GET method
     *
     * @param course the course to get students from
     * @return list of students
     */
    List<Student> getStudentsWithNamedCourse(Course course);

    /**
     * Returns a list of all students and its roles
     *
     * Too get all student with roles add /roles
     * too the URL
     *
     * Use the GET method
     *
     * @param student the students which should get roles
     * @return list of students
     */
    List<Student> getStudentWithRoles(Student student);

    /**
     * Adds a course too a student
     *
     * Too add course add /{studentId}/course/{courseId}
     * too the URL
     *
     * Use the POST method
     *
     * @param studentId id of the student
     * @param courseId id of the course
     */
    void addCourse(Long studentId, Long courseId);

    /**
     * Removes a course from a student
     *
     * Too remove course add /{studentId}/course/{courseId}
     * too the URL
     *
     * Use the DELETE method
     *
     * @param studentId id of the student
     * @param courseId if of the course
     */
    void removeCourse(Long studentId, Long courseId);

    /**
     * Adds a committee too a student
     *
     * Too add committee add /{studentId}/committee/{committeeId}
     * too the URL
     *
     * Use the POST method
     *
     * @param studentId id of the student
     * @param committeeId id of the committee
     */
    void addCommittee(Long studentId, Long committeeId);

    /**
     * Removes a committee from a student
     *
     * Too remove committee add /{studentId}/committee/{committeeId}
     * too the URL
     *
     * Use the DELETE method
     *
     * @param studentId id of the student
     * @param committeeId if of the committee
     */
    void removeCommittee(Long studentId, Long committeeId);

    /**
     * Adds a feed too a student
     *
     * Too add feed add /{studentId}/feed/{feedId}
     * too the URL
     *
     * Use the POST method
     *
     * @param studentId id of the student
     * @param feedId id of the feed
     */
    void addFeed(Long studentId, Long feedId);

    /**
     * Removes a feed from a student
     *
     * Too remove feed add /{studentId}/feed/{feedId}
     * too the URL
     *
     * Use the DELETE method
     *
     * @param studentId id of the student
     * @param feedId if of the feed
     */
    void removeFeed(Long studentId, Long feedId);

    /**
     * Adds a loan too a student
     *
     * Too add tutor add /{studentId}/loan/{loanId}
     * too the URL
     *
     * Use the POST method
     *
     * @param studentId id of the student
     * @param loanId id of the loan
     */
    void addLoan(Long studentId, Long loanId);

    /**
     * Removes a loan from a student
     *
     * Too remove loan add /{studentId}/loan/{loanId}
     * too the URL
     *
     * Use the DELETE method
     *
     * @param studentId id of the student
     * @param loanId if of the loan
     */
    void removeLoan(Long studentId, Long loanId);

    /**
     * Adds a role too the student
     *
     * Too add role add /{studentId}/role/{roleId}
     * too the URL
     *
     * Use the POST method
     *
     * @param studentId id of the student
     * @param roleId id of the role
     */
    void addRole(Long studentId, Long roleId);

    /**
     * Removes a role from the student
     *
     * Too remove role add /{studentId}/role/{roleId}
     * too the URL
     *
     * Use the DELETE method
     * 
     * @param studentId id of the student
     * @param roleId id of the role
     */
    void removeRole(Long studentId, Long roleId);

    /**
     * Removes a all roles from the student
     *
     * Too remove all roles add /{studentId}/roles
     * too the URL
     *
     * Use the DELETE method
     *
     * @param studId id for the student
     */
    void removeAllRolesFromStudent(Long studId);

    /**
     * Return 200 ok is student is in role, else 204, no content
     *
     * Too check if student has this role add /{studentId}/{roleName}
     * too the URL
     *
     * Use the GET method
     *
     * @param studId id for the student
     * @param roleName name for the role that the student should have
     */
    void isStudentInRole(Long studId, String roleName);

    /**
     * Adds a locker too the student
     *
     * Too add locker add /{studentId}/locker/{lockerId}
     * too the URL
     *
     * Use the POST method
     *
     * @param studentId the student's id of which the locker will be added
     * @param lockerId the locker's id of which the student will get
     */
    void addLocker(Long studentId, Long lockerId);

    /**
     * Removes a locker from the student
     *
     * Too remove locker add /{subjectId}/locker/{lockerId}
     * too the URL
     *
     * Use the DELETE method
     *
     * @param studentId the student's id of which the locker will be removed
     * @param lockerId the locker's id of which the student will get
     */
    void removeLocker(Long studentId, Long lockerId);
}
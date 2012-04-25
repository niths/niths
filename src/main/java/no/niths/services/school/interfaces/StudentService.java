package no.niths.services.school.interfaces;

import java.util.List;

import no.niths.domain.school.Student;
import no.niths.services.interfaces.GenericService;

public interface StudentService extends GenericService<Student> {

    /**
     * Returns a list with students with the provided course
     * @param name
     * @return
     */
    List<Student> getStudentsWithNamedCourse(String name);

    /**
     * Returns student with roles
     * @param student
     * @return
     */
    List<Student> getStudentsAndRoles(Student student);

    /**
     * Returns students by a specified column and a provided criteria
     * @param column
     * @param criteria
     * @return
     */
    List<Student> getStudentByColumn(String column, String criteria);

    /**
     * Returns a student that contains the provided token
     * @param token
     * @return
     */
    Student getStudentBySessionToken(String token);

    /**
     * Returns a student that contains the provided email
     * @param email
     * @return
     */
    Student getStudentByEmail(String email);

    /**
     * Gets a student with roles and the provided id
     * @param id
     * @return
     */
    Student getStudentWithRoles(Long id);

    /**
     * Returns a list with students with the provided course and the roles of the students
     * @param column the column of which the search will perform the search on
     * @param query the query to get the students
     * @return a list of matched students
     */
    List<Student> search(String column, String query);

    /**
     * Adds a course to the student 
     * @param studentId
     * @param courseId
     */
    void addCourse(Long studentId, Long courseId);

    /**
     * Removes a specified course from a student
     * @param studentId
     * @param courseId
     */
    void removeCourse(Long studentId, Long courseId);

    /**
     * Add committee 
     * @param studentId
     * @param committeeId
     */
    void addCommittee(Long studentId, Long committeeId);

    /**
     * Removes the committee
     * @param studentId
     * @param committeeId
     */
    void removeCommittee(Long studentId, Long committeeId);

    /**
     * Adds a student to a feed
     * @param studentId
     * @param feedId
     */
    void addFeed(Long studentId, Long feedId);

    /**
     * Removes a feed from a student
     * @param studentId
     * @param feedId
     */
    void removeFeed(Long studentId, Long feedId);

    /**
     * Adds a role to a student
     * @param studentId
     * @param roleId
     */
    void addRole(Long studentId, Long roleId);

    /**
     * Removes a role from a student
     * @param studentId
     * @param roleId
     */
    void removeRole(Long studentId, Long roleId);

    /**
     * Removes all roles from a student
     * @param studId
     */
    void removeAllRoles(Long studId);

    /**
     * Add s a loan to a student
     * @param studentId
     * @param loanId
     */
    void addLoan(Long studentId, Long loanId);

    /**
     * Remove a loan from a student
     * @param studentId
     * @param loanId
     */
    void removeLoan(Long studentId, Long loanId);

    /**
     * Updates roles for a student
     * @param studentId
     * @param roles
     */
    void updateRoles(Long studentId, Long[] roleIds);

    /**
     * Adds a locker to a student 
     * @param studentId
     * @param lockerId
     */
    void addLocker(Long studentId, Long lockerId);

    /**
     * Removes a lock from a student
     * @param studentId
     * @param lockerId
     */
    void removeLocker(Long studentId, Long lockerId);
}
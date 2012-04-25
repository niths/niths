package no.niths.services.school.interfaces;

import java.util.List;

import no.niths.domain.school.Student;
import no.niths.services.interfaces.GenericService;

public interface StudentService extends GenericService<Student> {

    /**
     * 
     * @param name
     * @return
     */
    List<Student> getStudentsWithNamedCourse(String name);

    /**
     * 
     * @param student
     * @return
     */
    List<Student> getStudentsAndRoles(Student student);

    /**
     * 
     * @param column
     * @param criteria
     * @return
     */
    List<Student> getStudentByColumn(String column, String criteria);

    /**
     * 
     * @param token
     * @return
     */
    Student getStudentBySessionToken(String token);

    /**
     * 
     * @param email
     * @return
     */
    Student getStudentByEmail(String email);

    /**
     * 
     * @param id
     * @return
     */
    Student getStudentWithRoles(Long id);

    /**
     * 
     * @param column the column of which the search will perform the search on
     * @param query the query to get the students
     * @return a list of matched students
     */
    List<Student> search(String column, String query);

    /**
     * 
     * @param studentId
     * @param courseId
     */
    void addCourse(Long studentId, Long courseId);

    /**
     * 
     * @param studentId
     * @param courseId
     */
    void removeCourse(Long studentId, Long courseId);

    /**
     * 
     * @param studentId
     * @param committeeId
     */
    void addCommittee(Long studentId, Long committeeId);

    /**
     * 
     * @param studentId
     * @param committeeId
     */
    void removeCommittee(Long studentId, Long committeeId);

    /**
     * 
     * @param studentId
     * @param feedId
     */
    void addFeed(Long studentId, Long feedId);

    /**
     * 
     * @param studentId
     * @param feedId
     */
    void removeFeed(Long studentId, Long feedId);

    /**
     * 
     * @param studentId
     * @param roleId
     */
    void addRole(Long studentId, Long roleId);

    /**
     * 
     * @param studentId
     * @param roleId
     */
    void removeRole(Long studentId, Long roleId);

    /**
     * 
     * @param studId
     */
    void removeAllRoles(Long studId);

    /**
     * 
     * @param studentId
     * @param loanId
     */
    void addLoan(Long studentId, Long loanId);

    /**
     * 
     * @param studentId
     * @param loanId
     */
    void removeLoan(Long studentId, Long loanId);

    /**
     * 
     * @param studentId
     * @param roles
     */
    void updateRoles(Long studentId, Long[] roleIds);

    /**
     * 
     * @param studentId
     * @param lockerId
     */
    void addLocker(Long studentId, Long lockerId);

    /**
     * 
     * @param studentId
     * @param lockerId
     */
    void removeLocker(Long studentId, Long lockerId);
}
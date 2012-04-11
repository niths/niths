package no.niths.application.rest.interfaces;

import java.util.List;

import no.niths.domain.Course;
import no.niths.domain.Student;

/**
 * Controller for student
 *
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
//	@Override
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TEST')")
//	public ArrayList<Student> getAll(Student domain);
	
	/**
	 * Returns a list of all students in a given course
	 * @param course the course to get students from
	 * @return list of students
	 */
	public List<Student> getStudentsWithNamedCourse(Course course);

    /**
     * Adds a course too a student
     *
     * @param studentId id of the student
     * @param courseId id of the course
     */
    public void addCourse(Long studentId, Long courseId);

    /**
     * Removes a course from a student
     *
     * @param studentId id of the student
     * @param courseId if of the course
     */
    public void removeCourse(Long studentId, Long courseId);

    /**
     * Adds a committee too a student
     *
     * @param studentId id of the student
     * @param committeeId id of the committee
     */
    public void addCommittee(Long studentId, Long committeeId);

    /**
     * Removes a committee from a student
     *
     * @param studentId id of the student
     * @param committeeId if of the committee
     */
    public void removeCommittee(Long studentId, Long committeeId);

    /**
     * Adds a feed too a student
     *
     * @param studentId id of the student
     * @param feedId id of the feed
     */
    public void addFeed(Long studentId, Long feedId);

    /**
     * Removes a feed from a student
     *
     * @param studentId id of the student
     * @param feedId if of the feed
     */
    public void removeFeed(Long studentId, Long feedId);

    /**
     * Adds a loaned game too a student
     *
     * @param studentId id of the student
     * @param loanedGameId id of the game
     */
   // public void addLoanedGame(Long studentId, Long loanedGameId);

    /**
     * Removes a loaned game from a student
     *
     * @param studentId id of the student
     * @param loanedGameId if of the game
     */
//    public void removeLoanedGame(Long studentId, Long loanedGameId);

    /**
     * Adds a loaned console too a student
     *
     * @param studentId id of the student
     * @param loanedConsoleId id of the console
     */
   // public void addLoanedConsole(Long studentId, Long loanedConsoleId);

    /**
     * Removes a loaned console from a student
     *
     * @param studentId id of the student
     * @param loanedConsoleId id of the console
     */
    //public void removeLoanedConsole(Long studentId, Long loanedConsoleId);
}

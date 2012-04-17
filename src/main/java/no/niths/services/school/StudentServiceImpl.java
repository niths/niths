package no.niths.services.school;

import java.util.ArrayList;
import java.util.List;

import no.niths.application.rest.exception.ObjectInCollectionException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.helper.Error;
import no.niths.application.rest.helper.Status;
import no.niths.common.LazyFixer;
import no.niths.common.MessageProvider;
import no.niths.common.SecurityConstants;
import no.niths.common.ValidationHelper;
import no.niths.domain.Domain;
import no.niths.domain.battlestation.Loan;
import no.niths.domain.school.Committee;
import no.niths.domain.school.Course;
import no.niths.domain.school.Feed;
import no.niths.domain.school.Student;
import no.niths.domain.security.Role;
import no.niths.infrastructure.battlestation.interfaces.LoanRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.interfaces.RoleRepository;
import no.niths.infrastructure.school.interfaces.CommitteeRepositorty;
import no.niths.infrastructure.school.interfaces.CourseRepository;
import no.niths.infrastructure.school.interfaces.FeedRepoistory;
import no.niths.infrastructure.school.interfaces.StudentRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.school.interfaces.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends AbstractGenericService<Student>
		implements StudentService {

	private static final Logger logger = LoggerFactory
			.getLogger(StudentServiceImpl.class);

	private LazyFixer<Student> lazyFixer = new LazyFixer<Student>();

	@Autowired
	private StudentRepository repo;

	@Autowired
	private CourseRepository courseRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private CommitteeRepositorty committeeService;

	@Autowired
	private FeedRepoistory feedRepo;
	
	@Autowired
	private LoanRepository loanRepo;

	public Long create(Student student) {
		Role r = new Role(SecurityConstants.R_STUDENT);
		List<Role> roles = roleRepo.getAll(r);
		if (!roles.isEmpty() && roles.size() == 1) {
			logger.debug("Role given to created student: "
					+ roles.get(0).getRoleName());
			student.setRoles(new ArrayList<Role>());
			student.getRoles().add(roles.get(0));
		}
		return repo.create(student);
	}

	@Override
	public Student getStudentByEmail(String email) {
		Student s = new Student(email);
		List<Student> all = getAll(s);
		lazyFixer.fetchChildren(all);
		return all.get(0);
	}

	@Override
	public Student getStudentBySessionToken(String token) {
		Student s = new Student();
		s.setSessionToken(token);
		List<Student> all = getAll(s);
		lazyFixer.fetchChildren(all);
		return all.get(0);
	}

	@Override
	public Student getStudentWithRoles(Long id) {
		Student s = repo.getById(id);
		if (s != null) {
			s.getRoles().size();
		}
		return s;
	}

	public List<Student> getStudentsWithNamedCourse(String name) {
		return repo.getStudentsWithNamedCourse(name);
	}

	@Override
	public List<Student> getStudentsAndRoles(Student s) {
		List<Student> list = repo.getAll(s);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).getRoles().size();
		}
		return list;
	}

	@Override
	public List<Student> getStudentByColumn(String column, String criteria) {
		return repo.getStudentByColumn(column, criteria);

	}

	@Override
	public GenericRepository<Student> getRepository() {
		return repo;
	}

	@Override
	public void addCourse(Long studentId, Long courseId) {
		Student student = validateStudent(repo.getById(studentId));
		checkIfObjectIsInCollection(student.getCourses(), courseId,Course.class);

		Course course = courseRepo.getById(courseId);
		ValidationHelper.isObjectNull(course, Course.class);

		student.getCourses().add(course);
		logger.debug(MessageProvider.buildStatusMsg(Course.class,
				Status.UPDATED));
	}

	@Override
	public void removeCourse(Long studentId, Long courseId) {
		Student student = validateStudent(repo.getById(studentId));

		boolean isRemoved = false;

		for (Course c : student.getCourses()) {
			if (c.getId() == courseId) {
				student.getCourses().remove(c);
				isRemoved = true;
				break;
			}
		}

		checkIfIsRemoved(isRemoved, Course.class);
	}

	@Override
	public void addCommittee(Long studentId, Long committeeId) {
		Student student = validateStudent(repo.getById(studentId));
		checkIfObjectIsInCollection(student.getCommittees(), committeeId,Committee.class);

		Committee committee = committeeService.getById(committeeId);
		ValidationHelper.isObjectNull(committee, Committee.class);

		student.getCommittees().add(committee);
		logger.debug(MessageProvider.buildStatusMsg(Committee.class,
				Status.UPDATED));
	}

	/**
	 * Helper method for checking if the the list element is a instance of
	 * Domain and then we can cast the list element to a Domain for using the
	 * getId() method
	 * 
	 * @throws ObjectInCollectionException
	 *             () if the object is found
	 * @param list
	 * @param id
	 */
	@SuppressWarnings("rawtypes")
	private void checkIfObjectIsInCollection(List list, long id,Class clazz) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) instanceof Domain) {
				Domain d = (Domain) list.get(i);
				if (d.getId() == id) {
					throw new ObjectInCollectionException(
							MessageProvider.buildErrorMsg(clazz,
									Error.OBJECT_IN_COLLECTION));
				}
			}
		}
	}

	private Student validateStudent(Student student) {
		ValidationHelper.isObjectNull(student, Student.class);
		return student;
	}

	@Override
	public void removeCommittee(Long studentId, Long committeeId) {
		Student student = validateStudent(repo.getById(studentId));
		boolean isRemoved = false;

		for (Committee c : student.getCommittees()) {
			if (c.getId() == committeeId) {
				student.getCommittees().remove(c);
				isRemoved = true;
				break;
			}
		}

		checkIfIsRemoved(isRemoved, Committee.class);
	}

	@Override
	public void addFeed(Long studentId, Long feedId) {
		Student student = validateStudent(repo.getById(studentId));
		checkIfObjectIsInCollection(student.getFeeds(), feedId,Feed.class);

		Feed feed = feedRepo.getById(feedId);
		ValidationHelper.isObjectNull(feed, Feed.class);

		student.getFeeds().add(feed);
		logger.debug(MessageProvider.buildStatusMsg(Feed.class, Status.UPDATED));
	}

	@Override
	public void removeFeed(Long studentId, Long feedId) {
		Student student = validateStudent(repo.getById(studentId));
		boolean isRemoved = false;

		for (int i = 0; i < student.getFeeds().size(); i++) {
			if (student.getFeeds().get(i).getId() == feedId) {
				student.getFeeds().remove(i);
				isRemoved = true;
				break;
			}
		}

		checkIfIsRemoved(isRemoved, Feed.class);
	}

	@SuppressWarnings("rawtypes")
	private void checkIfIsRemoved(boolean isRemoved, Class clazz) {
		if (!isRemoved) {
			String msg = MessageProvider.buildErrorMsg(clazz, Error.NOT_FOUND);
			logger.debug(msg);
			throw new ObjectNotFoundException(msg);
		}
	}

	@Override
	public void addRole(Long studentId, Long roleId) {
		Student student = validateStudent(repo.getById(studentId));
		checkIfObjectIsInCollection(student.getRoles(), roleId,Role.class);

		Role role = roleRepo.getById(roleId);
		ValidationHelper.isObjectNull(role, Role.class);

		student.getRoles().add(role);
		logger.debug(MessageProvider.buildStatusMsg(Role.class, Status.UPDATED));
	}

	@Override
	public void removeRole(Long studentId, Long roleId) {
		Student student = validateStudent(repo.getById(studentId));

		boolean isRemoved = false;
		for (Role r : student.getRoles()) {
			if (r.getId() == roleId) {
				student.getRoles().remove(r);

				isRemoved = true;
				break;
			}
		}
		checkIfIsRemoved(isRemoved, Role.class);
	}

	@Override
	public void removeAllRoles(Long studId) {
		Student student = validateStudent(repo.getById(studId));
		student.setRoles(null);
	}
	

	@Override
	public void addLoan(Long studentId, Long loanId) {
		Student student = validateStudent(repo.getById(studentId));
		checkIfObjectIsInCollection(student.getLoans(), loanId,Loan.class);

		Loan loan = loanRepo.getById(loanId);
		ValidationHelper.isObjectNull(loan, Loan.class);

		student.getLoans().add(loan);
		logger.debug(MessageProvider.buildStatusMsg(Loan.class, Status.UPDATED));
	
	}
	

	@Override
	public void removeLoan(Long studentId, Long loanId) {
		Student student = validateStudent(repo.getById(studentId));
        boolean isRemoved = false;
        for (Loan l: student.getLoans()) {
            if (l.getId() == loanId) {
                student.getLoans().remove(l);
                isRemoved = true;
                break;
            }
        }
    	checkIfIsRemoved(isRemoved, Loan.class);
	}
}

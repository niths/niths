package no.niths.services.school;

import java.util.ArrayList;
import java.util.List;

import no.niths.application.rest.helper.Status;
import no.niths.common.LazyFixer;
import no.niths.common.MessageProvider;
import no.niths.common.SecurityConstants;
import no.niths.common.ValidationHelper;
import no.niths.domain.battlestation.Loan;
import no.niths.domain.school.Committee;
import no.niths.domain.school.Course;
import no.niths.domain.school.Feed;
import no.niths.domain.school.Locker;
import no.niths.domain.school.Student;
import no.niths.domain.security.Role;
import no.niths.infrastructure.battlestation.interfaces.LoanRepository;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.interfaces.RoleRepository;
import no.niths.infrastructure.school.interfaces.CommitteeRepositorty;
import no.niths.infrastructure.school.interfaces.CourseRepository;
import no.niths.infrastructure.school.interfaces.FeedRepoistory;
import no.niths.infrastructure.school.interfaces.LockerRepository;
import no.niths.infrastructure.school.interfaces.StudentRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.ServiceHelper;
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

    private ServiceHelper<Student> helper  = new ServiceHelper<Student>();

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

    @Autowired
    private LockerRepository lockerRepo;

    public Long create(Student student) {
        Role role = new Role(SecurityConstants.R_STUDENT);
        List<Role> roles = roleRepo.getAll(role);
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
        Student student = new Student(email);
        List<Student> all = getAll(student);
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
        List<Student> list = repo.getStudentByColumn(column, criteria);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).getRoles().size();
        }

        return list;
    }

    @Override
    public GenericRepository<Student> getRepository() {
        return repo;
    }

    @Override
    public void addCourse(Long studentId, Long courseId) {
        Student student = helper.validate(
                repo.getById(studentId), Student.class);
        helper.checkIfObjectIsInCollection(
                student.getCourses(), courseId,Course.class);

        Course course = courseRepo.getById(courseId);
        ValidationHelper.isObjectNull(course, Course.class);

        student.getCourses().add(course);
        logger.debug(MessageProvider.buildStatusMsg(Course.class,
                Status.UPDATED));
    }

    @Override
    public void removeCourse(Long studentId, Long courseId) {
        Student student = helper.validate(
                repo.getById(studentId), Student.class);

        boolean isRemoved = false;

        for (Course course : student.getCourses()) {
            if (course.getId() == courseId) {
                student.getCourses().remove(course);
                isRemoved = true;
                break;
            }
        }

        helper.checkIfIsRemoved(isRemoved, Course.class);
    }

    @Override
    public void addCommittee(Long studentId, Long committeeId) {
        Student student = helper.validate(
                repo.getById(studentId),Student.class);
        helper.checkIfObjectIsInCollection(
                student.getCommittees(), committeeId,Committee.class);

        Committee committee = committeeService.getById(committeeId);
        ValidationHelper.isObjectNull(committee, Committee.class);

        student.getCommittees().add(committee);
        logger.debug(MessageProvider.buildStatusMsg(Committee.class,
                Status.UPDATED));
    }

    @Override
    public void removeCommittee(Long studentId, Long committeeId) {
        Student student = helper.validate(
                repo.getById(studentId),Student.class);
        boolean isRemoved = false;

        for (Committee committee : student.getCommittees()) {
            if (committee.getId() == committeeId) {
                student.getCommittees().remove(committee);
                isRemoved = true;
                break;
            }
        }

        helper.checkIfIsRemoved(isRemoved, Committee.class);
    }

    @Override
    public void addFeed(Long studentId, Long feedId) {
        Student student = helper.validate(
                repo.getById(studentId),Student.class);
        helper.checkIfObjectIsInCollection(
                student.getFeeds(), feedId,Feed.class);

        Feed feed = feedRepo.getById(feedId);
        ValidationHelper.isObjectNull(feed, Feed.class);

        
        student.getFeeds().add(feed);
        logger.debug(
                MessageProvider.buildStatusMsg(Feed.class, Status.UPDATED));
    }

    @Override
    public void removeFeed(Long studentId, Long feedId) {
        Student student =
                helper.validate(repo.getById(studentId),Student.class);
        boolean isRemoved = false;

        for (int i = 0; i < student.getFeeds().size(); i++) {
            if (student.getFeeds().get(i).getId() == feedId) {
                student.getFeeds().remove(i);
                isRemoved = true;
                break;
            }
        }

        helper.checkIfIsRemoved(isRemoved, Feed.class);
    }

    @Override
    public void addRole(Long studentId, Long roleId) {
        Student student =
                helper.validate(repo.getById(studentId),Student.class);
        helper.checkIfObjectIsInCollection(
                student.getRoles(), roleId,Role.class);

        Role role = roleRepo.getById(roleId);
        ValidationHelper.isObjectNull(role, Role.class);

        student.getRoles().add(role);
        logger.debug(MessageProvider.buildStatusMsg(
                Role.class, Status.UPDATED));
    }

    @Override
    public void removeRole(Long studentId, Long roleId) {
        Student student =
                helper.validate(repo.getById(studentId),Student.class);

        boolean isRemoved = false;
        for (Role role : student.getRoles()) {
            if (role.getId() == roleId) {
                student.getRoles().remove(role);

                isRemoved = true;
                break;
            }
        }
        helper.checkIfIsRemoved(isRemoved, Role.class);
    }

    @Override
    public void removeAllRoles(Long studId) {
        Student student = helper.validate(repo.getById(studId),Student.class);
        student.setRoles(null);
    }
    

    @Override
    public void addLoan(Long studentId, Long loanId) {
        Student student =
                helper.validate(repo.getById(studentId),Student.class);
        helper.checkIfObjectIsInCollection(
                student.getLoans(), loanId,Loan.class);

        Loan loan = loanRepo.getById(loanId);
        ValidationHelper.isObjectNull(loan, Loan.class);

        student.getLoans().add(loan);
        logger.debug(
                MessageProvider.buildStatusMsg(Loan.class, Status.UPDATED));
    
    }
    

    @Override
    public void removeLoan(Long studentId, Long loanId) {
        Student student =
                helper.validate(repo.getById(studentId),Student.class);
        boolean isRemoved = false;
        for (Loan loan: student.getLoans()) {
            if (loan.getId() == loanId) {
                student.getLoans().remove(loan);
                isRemoved = true;
                break;
            }
        }
        helper.checkIfIsRemoved(isRemoved, Loan.class);
    }

    @Override
    public void updateRoles(Long studentId, Long[] roleIds) {
        Student student =
                helper.validate(repo.getById(studentId),Student.class);
        student.getRoles().clear();
        List<Role> roles = roleRepo.getAll(null);
        
        for (Role role: roles) {
            for (long rId : roleIds) {
                if (role.getId() == rId) {
                    student.getRoles().add(role);
                }
            }
        }    
    }

    @Override
    public void addLocker(Long studentId, Long lockerId) {
        Student student =
                helper.validate(repo.getById(studentId), Student.class);
        helper.checkIfObjectIsInCollection(
                student.getLockers(), lockerId, Locker.class);

        Locker locker = lockerRepo.getById(lockerId);
        ValidationHelper.isObjectNull(locker, Locker.class);

        student.getLockers().add(locker);
        logger.debug(
                MessageProvider.buildStatusMsg(Locker.class, Status.UPDATED));
    }

    @Override
    public void removeLocker(Long studentId, Long lockerId) {
        Student student =
                helper.validate(repo.getById(studentId), Student.class);
        helper.checkIfIsRemoved(student.getLockers().remove(
                new Locker(lockerId)), Locker.class);
    }
}
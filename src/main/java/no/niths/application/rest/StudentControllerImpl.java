package no.niths.application.rest;

import java.util.ArrayList;
import java.util.List;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.StudentController;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.StudentList;
import no.niths.common.AppConstants;
import no.niths.common.SecurityConstants;
import no.niths.common.ValidationHelper;
import no.niths.domain.Committee;
import no.niths.domain.Console;
import no.niths.domain.Course;
import no.niths.domain.Feed;
import no.niths.domain.Game;
import no.niths.domain.Student;
import no.niths.services.interfaces.CommitteeService;
import no.niths.services.interfaces.ConsoleService;
import no.niths.services.interfaces.CourseService;
import no.niths.services.interfaces.FeedService;
import no.niths.services.interfaces.GameService;
import no.niths.services.interfaces.GenericService;
import no.niths.services.interfaces.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(AppConstants.STUDENTS)
public class StudentControllerImpl extends AbstractRESTControllerImpl<Student>
		implements StudentController {

	private static final Logger logger = LoggerFactory
			.getLogger(StudentControllerImpl.class);

	private StudentList studentList = new StudentList();

	@Autowired
	private StudentService service;

    @Autowired
	private CourseService courseService;

    @Autowired
	private CommitteeService committeeService;

    @Autowired
	private FeedService feedService;

    @Autowired
	private GameService gameService;

    @Autowired
	private ConsoleService consoleService;

	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR + " or (hasRole('ROLE_STUDENT') and principal.studentId == #id)")
	@RequestMapping(value = "{id}", method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public Student getById(@PathVariable Long id) {
		Student student = super.getById(id);
		if (student != null) {
			for (int i = 0; i < student.getCommittees().size(); i++) {
				student.getCommittees().get(i).setEvents(null);
				student.getCommittees().get(i).setLeaders(null);
				student.getCommittees().get(i).setMembers(null);
			}
			
			for (int i = 0; i < student.getCourses().size(); i++) {
				student.getCourses().get(i).setCourseRepresentatives(null);
				student.getCourses().get(i).setSubjects(null);
			}

			for(Feed f: student.getFeeds()){
				f.setStudent(null);
				f.setLocation(null);
			}
			
		}
		return student;
	}
	
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR + " or (hasRole('ROLE_STUDENT') and principal.studentId == #domain.id)")
	public void update(@RequestBody Student domain) {
		logger.warn(domain.getEmail() +" : "+ domain.getId() +" : " + domain.getFirstName()  +" : "+ domain.getLastName() +" : " + domain.getGender());
		super.update(domain);
	}
	
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public void create(@RequestBody Student domain) {
		super.create(domain);
	}
	
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR + " or (hasRole('ROLE_STUDENT') and principal.studentId == #id)")
	public void hibernateDelete(@PathVariable long id) {
		super.hibernateDelete(id);
	}
	

	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public ArrayList<Student> getAll(Student domain) {
		studentList = (StudentList) super.getAll(domain);
		return studentList;
	}
	
	@Override
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	public ArrayList<Student> getAll(Student domain, @PathVariable int firstResult,
			@PathVariable int maxResults) {
		studentList = (StudentList) super.getAll(domain, firstResult, maxResults);
		return studentList;
	}

	/**
	 * {@inheritDoc}
	 */
	@PreAuthorize(SecurityConstants.ADMIN_AND_SR)
	@RequestMapping(value = "course", method = RequestMethod.GET, headers = RESTConstants.ACCEPT_HEADER)
	@ResponseBody
	public List<Student> getStudentsWithNamedCourse(@RequestBody Course course) {
		String name = course.getName();
		logger.info(name);

		renewList(service.getStudentsWithNamedCourse(name));

		for (int i = 0; i < studentList.size(); i++) {
			studentList.get(i).setCommittees(null);
			studentList.get(i).setCourses(null);
		}
		return studentList;
	}

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "add/course/{studentId}/{courseId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Course Added")
    public void addCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        Student student = service.getById(studentId);
        ValidationHelper.isObjectNull(student, "Student does not exist");

        Course course = courseService.getById(courseId);
        ValidationHelper.isObjectNull(course, "Course does not exist");

        student.getCourses().add(course);
        service.update(student);
        logger.debug("Student updated");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "remove/course/{studentId}/{courseId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Course Removed")
    public void removeCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        Student student = service.getById(studentId);
        ValidationHelper.isObjectNull(student, "Student does not exist");

        boolean isRemoved = false;

        for (int i = 0; i < student.getCourses().size(); i++) {
            if (student.getCourses().get(i).getId() == courseId) {
                student.getCourses().remove(i);
                isRemoved = true;
                break;
            }
        }

        if (isRemoved) {
            service.update(student);
        } else {
            logger.debug("Course not found");
            throw new ObjectNotFoundException("Course not found");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "add/committee/{studentId}/{committeeId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Committee Added")
    public void addCommittee(@PathVariable Long studentId, @PathVariable Long committeeId) {
        Student student = service.getById(studentId);
        ValidationHelper.isObjectNull(student, "Student does not exist");

        Committee committee = committeeService.getById(committeeId);
        ValidationHelper.isObjectNull(committee, "Committee does not exist");

        student.getCommittees().add(committee);
        service.update(student);
        logger.debug("Student updated");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "remove/committee/{studentId}/{committeeId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Committee Removed")
    public void removeCommittee(@PathVariable Long studentId, @PathVariable Long committeeId) {
        Student student = service.getById(studentId);
        ValidationHelper.isObjectNull(student, "Student does not exist");

        boolean isRemoved = false;

        for (int i = 0; i < student.getCommittees().size(); i++) {
            if (student.getCommittees().get(i).getId() == committeeId) {
                student.getCommittees().remove(i);
                isRemoved = true;
                break;
            }
        }

        if (isRemoved) {
            service.update(student);
        } else {
            logger.debug("Committee not found");
            throw new ObjectNotFoundException("Committee not found");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "add/feed/{studentId}/{feedId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Feed Added")
    public void addFeed(@PathVariable Long studentId, @PathVariable Long feedId) {
        Student student = service.getById(studentId);
        ValidationHelper.isObjectNull(student, "Student does not exist");

        Feed feed = feedService.getById(feedId);
        ValidationHelper.isObjectNull(feed, "Feed does not exist");

        student.getFeeds().add(feed);
        service.update(student);
        logger.debug("Student updated");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "remove/feed/{studentId}/{feedId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Feed Removed")
    public void removeFeed(@PathVariable Long studentId, @PathVariable Long feedId) {
        Student student = service.getById(studentId);
        ValidationHelper.isObjectNull(student, "Student does not exist");

        boolean isRemoved = false;

        for (int i = 0; i < student.getFeeds().size(); i++) {
            if (student.getFeeds().get(i).getId() == feedId) {
                student.getFeeds().remove(i);
                isRemoved = true;
                break;
            }
        }

        if (isRemoved) {
            service.update(student);
        } else {
            logger.debug("Game not found");
            throw new ObjectNotFoundException("Game not found");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "add/loanedGame/{studentId}/{loanedGameId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Loaned game Added")
    public void addLoanedGame(@PathVariable Long studentId, @PathVariable Long loanedGameId) {
        Student student = service.getById(studentId);
        ValidationHelper.isObjectNull(student, "Student does not exist");

        Game loanedGame = gameService.getById(loanedGameId);
        ValidationHelper.isObjectNull(loanedGame, "Loaned game does not exist");

        student.getLoanedGames().add(loanedGame);
        service.update(student);
        logger.debug("Student updated");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "remove/loanedGame/{studentId}/{loanedGameId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Loaned game Removed")
    public void removeLoanedGame(@PathVariable Long studentId, @PathVariable Long loanedGameId) {
        Student student = service.getById(studentId);
        ValidationHelper.isObjectNull(student, "Student does not exist");

        boolean isRemoved = false;

        for (int i = 0; i < student.getLoanedGames().size(); i++) {
            if (student.getLoanedGames().get(i).getId() == loanedGameId) {
                student.getLoanedGames().remove(i);
                isRemoved = true;
                break;
            }
        }

        if (isRemoved) {
            service.update(student);
        } else {
            logger.debug("Loaned game not found");
            throw new ObjectNotFoundException("Loaned game not found");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "add/loanedConsole/{studentId}/{loanedConsoleId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Loaned console Added")
    public void addLoanedConsole(@PathVariable Long studentId, @PathVariable Long loanedConsoleId) {
        Student student = service.getById(studentId);
        ValidationHelper.isObjectNull(student, "Student does not exist");

        Console loanedConsole = consoleService.getById(loanedConsoleId);
        ValidationHelper.isObjectNull(loanedConsole, "Loaned console does not exist");

        student.getLoanedConsole().add(loanedConsole);
        service.update(student);
        logger.debug("Student updated");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "remove/loanedConsole/{studentId}/{loanedConsoleId}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK, reason = "Loaned console Removed")
    public void removeLoanedConsole(@PathVariable Long studentId, @PathVariable Long loanedConsoleId) {
        Student student = service.getById(studentId);
        ValidationHelper.isObjectNull(student, "Student does not exist");

        boolean isRemoved = false;

        for (int i = 0; i < student.getLoanedConsole().size(); i++) {
            if (student.getLoanedConsole().get(i).getId() == loanedConsoleId) {
                student.getLoanedConsole().remove(i);
                isRemoved = true;
                break;
            }
        }

        if (isRemoved) {
            service.update(student);
        } else {
            logger.debug("Loaned console not found");
            throw new ObjectNotFoundException("Loaned console not found");
        }
    }

    /**
	 * {@inheritDoc}
	 */
	@Override
	public GenericService<Student> getService() {
		return service;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListAdapter<Student> getList() {
		return studentList;
	}
}

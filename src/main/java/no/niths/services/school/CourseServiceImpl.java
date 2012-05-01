package no.niths.services.school;

import no.niths.application.rest.helper.Status;
import no.niths.common.helpers.MessageProvider;
import no.niths.common.helpers.ValidationHelper;
import no.niths.domain.school.Course;
import no.niths.domain.school.Student;
import no.niths.domain.school.Subject;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.school.interfaces.CourseRepository;
import no.niths.infrastructure.school.interfaces.StudentRepository;
import no.niths.infrastructure.school.interfaces.SubjectRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.school.interfaces.CourseService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Service Class for Course
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for addRepresentative,
 * removeRepresentative,
 * addSubject and removeSubject
 * </p>
 */
@Service
public class CourseServiceImpl extends AbstractGenericService<Course> implements
		CourseService {

    private Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

	@Autowired
	private CourseRepository courseRepository;

    @Autowired
	private StudentRepository studentRepository;

    @Autowired
	private SubjectRepository subjectRepository;

	@Override
	public GenericRepository<Course> getRepository() {
		return courseRepository;
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public void addRepresentative(Long courseId, Long studentId) {
        Course course = validate(courseRepository.getById(courseId), Course.class);
        checkIfObjectIsInCollection(course.getStudents(), studentId, Student.class);

        Student student = studentRepository.getById(studentId);
        ValidationHelper.isObjectNull(student, Student.class);

        course.getCourseRepresentatives().add(student);
        logger.debug(MessageProvider.buildStatusMsg(Student.class,
                Status.UPDATED));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeRepresentative(Long courseId, Long studentId) {
        Course course = validate(courseRepository.getById(courseId), Course.class);
        checkIfIsRemoved(course.getCourseRepresentatives().remove(new Student(studentId)),
                Course.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSubject(Long courseId, Long subjectId) {
        Course course = validate(courseRepository.getById(courseId), Course.class);
        checkIfObjectIsInCollection(course.getSubjects(), subjectId, Subject.class);

        Subject subject = subjectRepository.getById(subjectId);
        ValidationHelper.isObjectNull(subject, Subject.class);

        course.getSubjects().add(subject);
        logger.debug(MessageProvider.buildStatusMsg(Subject.class,
                Status.UPDATED));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeSubject(Long courseId, Long subjectId) {
        Course course = validate(courseRepository.getById(courseId), Course.class);
        checkIfIsRemoved(course.getSubjects().remove(new Subject(subjectId)),
                Course.class);
    }
}

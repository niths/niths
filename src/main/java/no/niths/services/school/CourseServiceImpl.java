package no.niths.services.school;

import no.niths.application.rest.exception.NotInCollectionException;
import no.niths.application.rest.exception.ObjectInCollectionException;
import no.niths.common.ValidationHelper;
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

    @Override
    public void addRepresentative(Long courseId, Long studentId) {
        Course course = super.getById(courseId);
        ValidationHelper.isObjectNull(course, Course.class);

        Student student = studentRepository.getById(studentId);
        ValidationHelper.isObjectNull(student, Student.class);

        if (!course.getCourseRepresentatives().contains(student)) {
            course.getCourseRepresentatives().add(student);
            logger.debug("Course updated");
        } else {
            throw new ObjectInCollectionException(
                    "Student already a representative");
        }
    }

    @Override
    public void removeRepresentative(Long courseId, Long studentId) {
        Course course = super.getById(courseId);
        ValidationHelper.isObjectNull(course, Course.class);

        boolean isRemoved = false;

        for (int i = 0; i < course.getCourseRepresentatives().size(); i++) {
            if (course.getCourseRepresentatives().get(i).getId() == studentId) {
                course.getCourseRepresentatives().remove(i);
                isRemoved = true;
            }
        }

        if (isRemoved) {
            logger.debug("Representative removed");
        } else {
            throw new NotInCollectionException(
                    "Student not a representative for that course");
        }
    }

    @Override
    public void addSubject(Long courseId, Long subjectId) {
        Course course = super.getById(courseId);
        ValidationHelper.isObjectNull(course, Course.class);

        Subject subject = subjectRepository.getById(subjectId);
        ValidationHelper.isObjectNull(subject, Subject.class);

        if (!course.getSubjects().contains(subject)) {
            course.getSubjects().add(subject);
            logger.debug("Course updated");
        } else {
            throw new ObjectInCollectionException(
                    "Subject is already added to the course");
        }
    }

    @Override
    public void removeSubject(Long courseId, Long subjectId) {
        Course course = super.getById(courseId);
        ValidationHelper.isObjectNull(course, Course.class);

        boolean isRemoved = false;

        for (int i = 0; i < course.getSubjects().size(); i++) {
            if (course.getSubjects().get(i).getId() == subjectId) {
                course.getSubjects().remove(i);
                isRemoved = true;
            }
        }

        if (isRemoved) {
            logger.debug("Subject removed");
        } else {
            throw new NotInCollectionException(
                    "Subject is not in this course");
        }
    }
}

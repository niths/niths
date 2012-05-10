package no.niths.domain.school;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import no.niths.domain.school.Course;
import no.niths.domain.school.Student;
import no.niths.domain.school.Subject;

import org.junit.BeforeClass;
import org.junit.Test;

public class CourseTest {
    private static final Long ID = 1L;
    private static final String NAME = "Programmering";
    private static final String DESCRIPTION = "Programmeringsfaget";
    
    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @Test
    public void testShouldGenerateNewCourse() {
        Course course = new Course();
        course.setName(NAME);
        course.setDescription(DESCRIPTION);

        assertThat(NAME, is(equalTo(course.getName())));
        assertThat(DESCRIPTION, is(equalTo(course.getDescription())));
    }

    @Test
    public void testTwoEqualCourses() {
        Course course = new Course(ID, NAME, DESCRIPTION);

        Course equalCourse = course;

        assertThat(true, is(equalTo(course.equals(equalCourse))));
    }

    @Test
    public void testTwoCoursesWhichIsNotEqual() {
        Course course = new Course(ID, NAME, DESCRIPTION);

        Course notEqualCourse = new Course(NAME, DESCRIPTION);

        assertThat(false, is(equalTo(course.equals(notEqualCourse))));
    }

    @Test
    public void testEqualsBetweenNotEqualObjects() {
        Course course = new Course(ID, NAME, DESCRIPTION);

        Student student = new Student();

        assertThat(false, is(equalTo(course.equals(student))));
    }

    @Test
    public void testValidationOfCorrectCourseValues() {
        Course course = new Course(NAME, DESCRIPTION);

        Set<ConstraintViolation<Course>> constraintViolations = validator
                        .validate(course);

        assertThat(0, is(equalTo(constraintViolations.size())));        
    }

    @Test
    public void testValidationOfIncorrectCourseValues() {
        Course course = new Course("X", DESCRIPTION);

        Set<ConstraintViolation<Course>> constraintViolations = validator
                        .validate(course);

        constraintViolations = validator.validate(course);
        assertThat(1, is(equalTo(constraintViolations.size())));        
    }

    @Test
    public void testGettingSubjectFromCourse() {
        Subject subject = new Subject();
        
        List<Subject> subjectList = new ArrayList<Subject>();
        subjectList.add(subject);

        Course course = new Course();
        course.setSubjects(subjectList);
    }
}

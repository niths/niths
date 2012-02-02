package no.niths.domain;

import no.niths.domain.Course;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CourseTest {

    private static final Logger logger = LoggerFactory
            .getLogger(StudentTest.class);

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testCourseValues() {

        Course course = new Course("Programmering", "Programmeringsfaget");

        // Should pass validation
        Set<ConstraintViolation<Course>> constraintViolations = validator
                .validate(course);

        assertThat(0, is(equalTo(constraintViolations.size())));

        // Should not pass validation
        course.setName("P");
        constraintViolations = validator.validate(course);
        assertThat(1, is(equalTo(constraintViolations.size())));

        //Prints the error message
        logger.debug(constraintViolations.iterator().next().getMessage());

        Course course2 = new Course("VF", "Fag med valideringsfeil");
        constraintViolations = validator.validate(course2);
        assertThat(1, is(equalTo(constraintViolations.size())));

        course2.setName("UVF");
        constraintViolations = validator.validate(course2);
        assertThat(0, is(equalTo(constraintViolations.size())));
    }
}

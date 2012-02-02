package no.niths.domain;

import no.niths.domain.CommitteeEvent;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.GregorianCalendar;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CommitteeEventTest {

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

        CommitteeEvent committeeEvent = new CommitteeEvent(1, "Spillkveld", "Utvalg for Fantasiske Fritidssysler har brettspillaften", new GregorianCalendar());

        // Should pass validation
        Set<ConstraintViolation<CommitteeEvent>> constraintViolations = validator
                .validate(committeeEvent);

        assertThat(0, is(equalTo(constraintViolations.size())));

        // Should not pass validation
        committeeEvent.setName("SK");
        constraintViolations = validator.validate(committeeEvent);
        assertThat(1, is(equalTo(constraintViolations.size())));

        //Prints the error message
        logger.debug(constraintViolations.iterator().next().getMessage());

        CommitteeEvent committeeEvent2 = new CommitteeEvent(1, "SK", "Utvalg for Fantasiske Fritidssysler har brettspillaften", new GregorianCalendar());
        constraintViolations = validator.validate(committeeEvent2);
        assertThat(1, is(equalTo(constraintViolations.size())));

        committeeEvent2.setName("Spillkveld");
        constraintViolations = validator.validate(committeeEvent2);
        assertThat(0, is(equalTo(constraintViolations.size())));
    }
}

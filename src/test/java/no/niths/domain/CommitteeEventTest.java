package no.niths.domain;

import no.niths.domain.Event;
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

		Event committeeEvent = new Event("Spillkveld",
				"Utvalg for Fantasiske Fritidssysler har brettspillaften",
				new GregorianCalendar(),null);

		// Should pass validation
		Set<ConstraintViolation<Event>> constraintViolations = validator
				.validate(committeeEvent);

		assertThat(0, is(equalTo(constraintViolations.size())));

		// Should not pass validation
		committeeEvent.setName("SK");
		constraintViolations = validator.validate(committeeEvent);
		assertThat(1, is(equalTo(constraintViolations.size())));

		// Prints the error message
		logger.debug(constraintViolations.iterator().next().getMessage());

		Event committeeEvent2 = new Event("SK",
				"Utvalg for Fantasiske Fritidssysler har brettspillaften",
				new GregorianCalendar(),null);
		constraintViolations = validator.validate(committeeEvent2);
		assertThat(1, is(equalTo(constraintViolations.size())));

		committeeEvent2.setName("Spillkveld");
		constraintViolations = validator.validate(committeeEvent2);
		assertThat(0, is(equalTo(constraintViolations.size())));
	}
}

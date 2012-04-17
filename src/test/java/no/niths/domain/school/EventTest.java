package no.niths.domain.school;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.GregorianCalendar;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import no.niths.domain.school.Course;
import no.niths.domain.school.Event;

import org.junit.BeforeClass;
import org.junit.Test;

public class EventTest {
	private static final Long ID = 1L;
	private static final String NAME = "Spillkveld";
	private static final String DESCRIPTION = "Utvalg for Fantasiske Fritidssysler har brettspillaften";
	private static final GregorianCalendar START_TIME = new GregorianCalendar();
	private static final GregorianCalendar END_TIME = new GregorianCalendar();

	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testShouldGenerateNewEvent() {
		Event event = new Event();
		event.setName(NAME);
		event.setDescription(DESCRIPTION);
		event.setStartTime(START_TIME);
		event.setEndTime(END_TIME);

		assertThat(NAME, is(equalTo(event.getName())));
		assertThat(DESCRIPTION, is(equalTo(event.getDescription())));
		assertThat(START_TIME, is(equalTo(event.getStartTime())));
		assertThat(END_TIME, is(equalTo(event.getEndTime())));

		assertThat(false, is(equalTo(event.isEmpty())));
	}

	@Test
	public void testTwoEqualEvents() {
		Event event = new Event(ID, NAME, DESCRIPTION, START_TIME, END_TIME);

		Event equalEvent = event;

		assertThat(true, is(equalTo(event.equals(equalEvent))));
	}

	@Test
	public void testTwoEventsWhichIsNotEqual() {
		Event event = new Event(ID, NAME, DESCRIPTION, START_TIME, END_TIME);

		Event notEqualEvent = new Event(NAME, DESCRIPTION, START_TIME, END_TIME);

		assertThat(false, is(equalTo(event.equals(notEqualEvent))));
	}

	@Test
	public void testEqualsBetweenNotEqualObjects() {
		Event event = new Event(ID, NAME, DESCRIPTION, START_TIME, END_TIME);

		Course course = new Course();

		assertThat(false, is(equalTo(event.equals(course))));
	}

	@Test
	public void testEmptyEventObject() {
		Event event = new Event();

		assertThat(true, is(equalTo(event.isEmpty())));
	}

	@Test
	public void testValidationOfCorectEventValues() {
		Event event = new Event(NAME, DESCRIPTION, START_TIME, END_TIME);

		Set<ConstraintViolation<Event>> constraintViolations = validator
				.validate(event);

		assertThat(0, is(equalTo(constraintViolations.size())));
	}

	@Test
	public void testValidationOfIncorectEventValues() {
		Event event = new Event("SK", DESCRIPTION, START_TIME, END_TIME);

		Set<ConstraintViolation<Event>> constraintViolations = validator
				.validate(event);

		constraintViolations = validator.validate(event);
		assertThat(1, is(equalTo(constraintViolations.size())));
	}
}

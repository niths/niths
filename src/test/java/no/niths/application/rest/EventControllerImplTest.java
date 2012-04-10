package no.niths.application.rest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.helper.TimeDTO;
import no.niths.application.rest.interfaces.EventController;
import no.niths.application.rest.interfaces.LocationController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Event;
import no.niths.domain.location.Location;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class EventControllerImplTest {

	@Autowired
	private EventController controller;

	@Autowired
	private LocationController locationController;

	private Location testLocation;

	private Event testEvent01;
	private Event testEvent02;
	private Event testEvent03;
	private Event testEvent04;

	private GregorianCalendar startTime01 = new GregorianCalendar(2012,
			Calendar.APRIL, 10, 12, 20);
	private GregorianCalendar endTime01 = new GregorianCalendar(2012,
			Calendar.APRIL, 11, 12, 20);
	private GregorianCalendar startTime02 = new GregorianCalendar(2012,
			Calendar.APRIL, 12, 12, 20);
	private GregorianCalendar endTime02 = new GregorianCalendar(2012,
			Calendar.APRIL, 13, 12, 20);
	private GregorianCalendar startTime03 = new GregorianCalendar(2012,
			Calendar.MAY, 10, 12, 20);
	private GregorianCalendar endTime03 = new GregorianCalendar(2012,
			Calendar.MAY, 15, 12, 20);
	private GregorianCalendar startTime04 = new GregorianCalendar(2012,
			Calendar.APRIL, 17, 12, 20);
	private GregorianCalendar endTime04 = new GregorianCalendar(2012,
			Calendar.APRIL, 17, 14, 20);

	@Before
	public void setUp() throws Exception {
		testEvent01 = new Event("NITH Party", "awesome", startTime01, endTime01);
		testEvent02 = new Event("LUG event", "awesome", startTime02, endTime02);
		testEvent03 = new Event("Windows fest", "awesome", startTime03,
				endTime03);
		testEvent04 = new Event("Mac attack", "awesome", startTime04, endTime04);

		controller.create(testEvent01);
		controller.create(testEvent02);
		controller.create(testEvent03);
		controller.create(testEvent04);

		testLocation = new Location("School", 12.123, 12.3122);
		locationController.create(testLocation);
	}

	@After
	public void tearDown() throws Exception {
		controller.hibernateDelete(testEvent01.getId());
		controller.hibernateDelete(testEvent02.getId());
		controller.hibernateDelete(testEvent03.getId());
		controller.hibernateDelete(testEvent04.getId());
		locationController.hibernateDelete(testLocation.getId());
	}

	@Test
	public void testGetByIdLong() {
		Event event = controller.getById(testEvent01.getId());
		assertEquals(testEvent01, event);
	}

	@Test(expected = ObjectNotFoundException.class)
	public void testGetByInvalidId_shouldThrowException() {
		controller.getById(new Long(-1));
	}

	@Test
	public void testGetAllEvent() {
		ArrayList<Event> events = controller.getAll(new Event());
		assertEquals(4, events.size());
	}

	@Test
	public void testGetAllEvent_withParams() {
		// TODO create test here
	}

	@Test
	public void testGetAllEventIntInt() {
		ArrayList<Event> events = controller.getAll(new Event(), 1, 3);
		assertEquals(3, events.size());
	}

	@Test
	public void testUpdateEvent() {
		Event event = new Event();
		event.setId(testEvent04.getId());
		event.setDescription("new Description");

		event.setName("new Name");
		controller.update(event);
		Event temp = controller.getById(testEvent04.getId());
		assertEquals(event.getName(), temp.getName());
		assertEquals(event.getDescription(), temp.getDescription());

	}

	@Test
	public void testGetEventsByTag() {
		// TODO create test here
	}

	@Test
	public void testAddAndRemoveLocation() {
		// TODO create test here
	}

	@Test
	public void testGetEventsBetweenDates() {
		String start ="09/04/2012-10:55";
		String end="09/05/2012-10:55";

		TimeDTO tdto = new TimeDTO(start, end);
		
		System.out.println(tdto.getEndTimeCal().getTime());
		System.out.println(tdto.getStartTimeCal().getTime());
		
		List<Event> events = controller.getEventsBetweenDates(tdto);
		
		assertEquals(3, events.size());
	}
}

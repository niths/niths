package no.niths.infrastructure;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;
import java.util.List;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Event;
import no.niths.domain.location.Location;
import no.niths.infrastructure.interfaces.EventRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
@Transactional
public class EventRepositoryTest {

	@Autowired
	private EventRepository eventRepo;

	@Test
	public void testCRUD() {
		// create
		int size = eventRepo.getAll(null).size();

		Event event = new Event("event");
		event.setName("Joe");
		eventRepo.create(event);
		assertEquals(size + 1, eventRepo.getAll(null).size());
		assertEquals(event, eventRepo.getById(event.getId()));

		// update time
		GregorianCalendar newDate = new GregorianCalendar(2012, 2, 22, 22, 30);
		event.setStartTime(newDate);
		eventRepo.update(event);
		event = eventRepo.getById(event.getId());
		assertEquals(newDate, event.getStartTime());

		eventRepo.delete(event.getId());
		assertEquals(size, eventRepo.getAll(null).size());

	}

	@Test
	public void testGetAllWithParams() {

		GregorianCalendar cal = new GregorianCalendar(2012, 11, 23, 22, 21, 23);
		Event c1 = new Event("LUG Party", "Linux", null, null);
		Event c2 = new Event("Halloween Fest", "Skummelt selskap", null, null);
		Event c3 = new Event("Party", "Rock on brah", cal, null);

		eventRepo.create(c1);
		eventRepo.create(c2);
		eventRepo.create(c3);

		c1.setDescription(null);
		assertEquals(1, eventRepo.getAll(c1).size());

		Event c4 = new Event();
		c4.setStartTime(cal);
		assertEquals(1, eventRepo.getAll(c4).size());

		assertEquals(3, eventRepo.getAll(new Event()).size());
	}

	@Test
	public void testGetEventsByTag() {
		GregorianCalendar cal = new GregorianCalendar(2012, 11, 23, 22, 21, 23);
		Event c1 = new Event("LUG Party", "Linux", null, null);
		Event c2 = new Event("Halloween Fest", "Skummelt selskap", null, null);
		Event c3 = new Event("Party", "Rock on brah", cal, null);

		c1.setTags("Linux, FUDORA,a KROA");
		c2.setTags("FadderUKA, Kroa");
		c3.setTags("LAXa");
		eventRepo.create(c1);
		eventRepo.create(c2);
		eventRepo.create(c3);

		List<Event> e = eventRepo.getEventsByTag("L");
		assertEquals(2, e.size());

		//
		// e = eventRepo.getEventsByTag("Kroa&K");
		// assertEquals(2, e.size());

		e = eventRepo.getEventsByTag("a");
		assertEquals(3, e.size());
	}
	
	@Test 
	public void testEventLocation(){
		GregorianCalendar cal = new GregorianCalendar(2012, 11, 23, 22, 21, 23);
		Event event = new Event("LUG Party", "Linux", cal, null);
		Location loc = new Location("Oslo",10.2304,90.2030);
		event.setLocation(loc);
		
		eventRepo.create(event);
		Event temp = eventRepo.getAll(event).get(0);
		assertEquals(event,temp);
		
		
		assertEquals(loc, temp.getLocation());
		
		// update 
		event.setEndTime(cal);		
		temp = eventRepo.getAll(event).get(0);
		
		assertEquals(event.getEndTime(), temp.getEndTime());
	
		assertEquals(loc, temp.getLocation());
	}
}

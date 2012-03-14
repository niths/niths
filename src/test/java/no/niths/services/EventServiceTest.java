package no.niths.services;

import static org.junit.Assert.assertEquals;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Event;
import no.niths.services.interfaces.EventsService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class EventServiceTest {

	private static final Logger logger = LoggerFactory
			.getLogger(EventServiceTest.class);
	
	@Autowired
	private EventsService eventService;
	
	@Test
	public void testCRUD(){
		int size = eventService.getAll(null).size();
		Event e1 = new Event("the name xxx");
		eventService.create(e1);
		assertEquals(size + 1, eventService.getAll(null).size());
		
		Event temp = new Event("the name xxx");
		assertEquals(1, eventService.getAll(temp).size());
		
		e1.setName("the new name xxx");
		eventService.update(e1);
		
		assertEquals("the new name xxx", eventService.getById(e1.getId()).getName());
		
		eventService.hibernateDelete(e1.getId());
		assertEquals(size, eventService.getAll(null).size());
		
		
	}
	
}

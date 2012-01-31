package no.niths.test.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.GregorianCalendar;

import no.niths.common.config.AppConfig;
import no.niths.domain.CommitteeEvents;
import no.niths.infrastructure.interfaces.CommitteeEventsRepository;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class CommitteeEventsRepositoryTest {
	@Autowired
	private CommitteeEventsRepository eventRepo;

	@Test
	public void testCRUD() {
		// create
		CommitteeEvents event = new CommitteeEvents();
		eventRepo.create(event);
		assertEquals(event, eventRepo.getCommitteeEventsById(event.getId()));

		// update time
		GregorianCalendar newDate = new GregorianCalendar(2012, 2, 22, 22, 30);
		event.setDateAndTime(newDate);
		eventRepo.update(event);
		event = eventRepo.getCommitteeEventsById(event.getId());
		assertEquals(event.getDateAndTime(), event.getDateAndTime());

		eventRepo.delete(event);

		assertNull(eventRepo.getCommitteeEventsById(event.getId()));
	}

}

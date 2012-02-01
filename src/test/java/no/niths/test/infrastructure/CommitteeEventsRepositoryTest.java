package no.niths.test.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.GregorianCalendar;

import no.niths.common.config.AppConfig;
import no.niths.common.config.HibernateConfig;
import no.niths.domain.CommitteeEvent;
import no.niths.infrastructure.interfaces.CommitteeEventsRepository;
import no.niths.test.common.config.TestAppConfig;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= { TestAppConfig.class, HibernateConfig.class})
@Transactional
public class CommitteeEventsRepositoryTest {
	
	@Autowired
	private CommitteeEventsRepository eventRepo;

	@Test
	@Rollback(true)
	public void testCRUD() {
		// create
		CommitteeEvent event = new CommitteeEvent();
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

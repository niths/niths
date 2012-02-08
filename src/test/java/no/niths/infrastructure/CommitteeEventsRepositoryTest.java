package no.niths.infrastructure;

import static org.junit.Assert.assertEquals;

import java.util.GregorianCalendar;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.CommitteeEvent;
import no.niths.domain.CommitteeEvent;
import no.niths.infrastructure.interfaces.CommitteeEventsRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= { TestAppConfig.class, HibernateConfig.class})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager")  
public class CommitteeEventsRepositoryTest {
	
	@Autowired
	private CommitteeEventsRepository eventRepo;

	@Test
	//@Rollback(true)
	public void testCRUD() {
		// create
		int size = eventRepo.getAll().size();
		
		CommitteeEvent event = new CommitteeEvent();
		eventRepo.create(event);
		assertEquals(size + 1, eventRepo.getAll().size());
		assertEquals(event, eventRepo.getCommitteeEventsById(event.getId()));

		// update time
		GregorianCalendar newDate = new GregorianCalendar(2012, 2, 22, 22, 30);
		event.setDateAndTime(newDate);
		eventRepo.update(event);
		event = eventRepo.getCommitteeEventsById(event.getId());
		assertEquals(event.getDateAndTime(), event.getDateAndTime());

		eventRepo.delete(event.getId());
		assertEquals(size, eventRepo.getAll().size());
		
	}

	
	@Test

	public void testGetAllWithCreateCritera(){
		
		GregorianCalendar cal = new GregorianCalendar(2012,11,23,22,21,23);
		CommitteeEvent c1 = new CommitteeEvent("LUG Party", "Linux", null);
		CommitteeEvent c2 = new CommitteeEvent("Halloween Fest", "Skummelt selskap", null);
		CommitteeEvent c3 = new CommitteeEvent("Party", "Rock on brah", cal);
		
		eventRepo.create(c1);
		eventRepo.create(c2);
		eventRepo.create(c3);
		
		
		c1.setDescription(null);
		assertEquals(1, eventRepo.getAll(c1).size());
	
		CommitteeEvent c4 = new CommitteeEvent();
		c4.setDateAndTime(cal);
		assertEquals(1, eventRepo.getAll(c4).size());
		
		assertEquals(3, eventRepo.getAll(new CommitteeEvent()).size());
	}
}

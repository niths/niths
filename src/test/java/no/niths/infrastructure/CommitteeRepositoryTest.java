package no.niths.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Committee;
import no.niths.domain.CommitteeEvent;
import no.niths.infrastructure.interfaces.CommitteesRepository;

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
public class CommitteeRepositoryTest {

	@Autowired
	private CommitteesRepository committeeRepo;
			
	@Test
	//@Rollback(true)
	public void testCRUD() {
		Committee committee = new Committee("LUG", "Linux");

		committee.setId(committeeRepo.create(committee));
		assertEquals(committee, committeeRepo.getCommitteeById(committee.getId()));
	
		committee.setName("LINUXs");
		committeeRepo.update(committee);

		assertEquals(committee, committeeRepo.getCommitteeById(committee.getId()));
		
		committeeRepo.delete(committee.getId());
	
		assertNull(committeeRepo.getCommitteeById(committee.getId()));
	}
	

	@Test
	@Rollback(true)
	public void testEventJoin(){
		
		CommitteeEvent event = new CommitteeEvent();
		Committee committee = new Committee("LUG", "Linux");
		committee.getEvents().add(event);
		
		committeeRepo.create(committee);
		Committee temp = committeeRepo.getCommitteeById(committee.getId());
		assertEquals(committee, temp);
		
		assertEquals(1, temp.getEvents().size());
		
	}
}

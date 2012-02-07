package no.niths.infrastructure;

import static org.junit.Assert.assertEquals;
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
	@Rollback(true)
	public void testCRUD() {
		int size = committeeRepo.getAllCommittees().size();
		Committee committee = new Committee("LUG", "Linux");

		committee.setId(committeeRepo.create(committee));
		assertEquals(size + 1, committeeRepo.getAllCommittees().size());
		assertEquals(committee, committeeRepo.getCommitteeById(committee.getId()));
	
		committee.setName("LINUXs");
		committeeRepo.update(committee);

		assertEquals(committee, committeeRepo.getCommitteeById(committee.getId()));
		
		committeeRepo.delete(committee.getId());
		
		assertEquals(size, committeeRepo.getAllCommittees().size());
	}
	

	@Test
	@Rollback(true)
	public void testGetAllWithCreateCritera(){
		
		Committee c1 = new Committee("LUG", "23");
		Committee c2 = new Committee("LAG", "Linux");
		Committee c3 = new Committee("ads", "Linux");
		
		committeeRepo.create(c1);
		committeeRepo.create(c2);
		committeeRepo.create(c3);
		
		
		c1.setDescription(null);
		assertEquals(1, committeeRepo.getAllCommittees(c1).size());
	
		c3.setName(null);
		assertEquals(2, committeeRepo.getAllCommittees(c3).size());
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

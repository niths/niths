package no.niths.application.rest;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.FadderGroupController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.FadderGroup;
import no.niths.domain.Student;
import no.niths.infrastructure.interfaces.FadderGroupRepository;
import no.niths.infrastructure.interfaces.StudentRepository;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
@Transactional
@TransactionConfiguration(
        transactionManager = TestAppConfig.TRANSACTION_MANAGER)
public class FadderGroupControllerTest {
	
	@Autowired
	private FadderGroupController fadderController;
	
	@Autowired
	private StudentRepository studRepo;
	
	@Autowired FadderGroupRepository fadderRepo;
	
	@Test
	public void testAddAndRemoveLeaders(){
		Student s1 = new Student("mail@mail.com");
		Student s2 = new Student("mail2@mail.com");
		studRepo.create(s1);
		studRepo.create(s2);
		
		FadderGroup g1 = new FadderGroup();
		fadderRepo.create(g1);
		
		assertEquals(g1, fadderController.getById(g1.getId()));
		
		fadderController.addLeaderToAGroup(g1.getId(), s1.getId());
		
		assertEquals(1, fadderController.getById(g1.getId()).getLeaders().size());
		
		fadderController.removeLeaderFromAGroup(g1.getId(), s1.getId());
		
		assertEquals(true, fadderController.getById(g1.getId()).getLeaders().isEmpty());
		
		fadderController.addLeaderToAGroup(g1.getId(), s1.getId());
		fadderController.addLeaderToAGroup(g1.getId(), s2.getId());
		assertEquals(2, fadderController.getById(g1.getId()).getLeaders().size());
		
		fadderController.removeAllLeadersFromAGroup(g1.getId());
		assertEquals(true, fadderController.getById(g1.getId()).getLeaders().isEmpty());
	}
	
	@Test
	public void testAddAndRemoveChildren(){
		Student s1 = new Student("mail@mail.com");
		Student s2 = new Student("mail2@mail.com");
		studRepo.create(s1);
		studRepo.create(s2);
		
		FadderGroup g1 = new FadderGroup();
		fadderRepo.create(g1);
		
		assertEquals(true, fadderController.getById(g1.getId()).getFadderChildren().isEmpty());
		
		fadderController.addChildToAGroup(g1.getId(), s1.getId());
		fadderController.addChildToAGroup(g1.getId(), s2.getId());
		
		assertEquals(2, fadderController.getById(g1.getId()).getFadderChildren().size());
		
		fadderController.removeChildFromAGroup(g1.getId(), s1.getId());
		assertEquals(1, fadderController.getById(g1.getId()).getFadderChildren().size());
		
		fadderController.removeAllChildrenFromAGroup(g1.getId());
		assertEquals(true, fadderController.getById(g1.getId()).getFadderChildren().isEmpty());
		
	}
	
	@Test
	public void testCRUD(){
		int size = 0;
		try{
			size = fadderController.getAll(null).size();
		}catch(ObjectNotFoundException e){
			//Do nothing
		}
		
		FadderGroup group = new FadderGroup(1337);
		
		fadderController.create(group);
		
		assertEquals(size + 1, fadderController.getAll(null).size());
		
		assertEquals(1, fadderController.getAll(group).size());
		
		group.setGroupNumber(1338);
		fadderController.update(group);
		
		assertEquals(new Integer(1338), fadderController.getAll(group).get(0).getGroupNumber());
	}
	
}

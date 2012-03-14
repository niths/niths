package no.niths.infrastructure;

import static org.junit.Assert.assertEquals;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.FadderGroup;
import no.niths.domain.Student;
import no.niths.infrastructure.interfaces.FadderGroupRepository;
import no.niths.infrastructure.interfaces.StudentRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= { TestAppConfig.class, HibernateConfig.class})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager")
public class FadderGroupRepositoryTest {
	
	@Autowired
	private FadderGroupRepository fadderRepo;
	
	@Autowired
	private StudentRepository studRepo;

	@Test
	public void testCRUD(){
		
		int size = fadderRepo.getAll(null).size();
		
		FadderGroup group = new FadderGroup(924);
		
		fadderRepo.create(group);
		
		assertEquals(size + 1, fadderRepo.getAll(null).size());
		
		assertEquals(group, fadderRepo.getById(group.getId()));
		
		group.setGroupNumber(1337);
		fadderRepo.update(group);
		
		assertEquals(new Integer(1337), fadderRepo.getById(group.getId()).getGroupNumber());
		
		fadderRepo.delete(group.getId());
		assertEquals(size, fadderRepo.getAll(null).size());
		
	}
	
	@Test
	public void testAddAndRemoveLeaders(){
		
		int studSize = studRepo.getAll(null).size();
		
		Student s1 = new Student("mail@nith.no");
		Student s2 = new Student("mail2@nith.no");
//		Student s3 = new Student("mail3@nith.no");
//		Student s4 = new Student("mail4@nith.no");
		
		studRepo.create(s1);
		studRepo.create(s2);
		
		FadderGroup group = new FadderGroup(924);
		fadderRepo.create(group);
		int leaderSize = fadderRepo.getById(group.getId()).getLeaders().size();
		//Add two leaders
		group.getLeaders().add(s1);
		group.getLeaders().add(s2);
		fadderRepo.update(group);
		//Check if persisted
		assertEquals(leaderSize + 2, fadderRepo.getById(group.getId()).getLeaders().size());
		
		assertEquals(studSize + 2, studRepo.getAll(null).size());
		
		//Remove a leader from the group
		group.getLeaders().remove(s2);
		fadderRepo.update(group);
		
		//Check if it was removed
		assertEquals(leaderSize + 1, fadderRepo.getById(group.getId()).getLeaders().size());
		
		//Student should still be in the DB
		assertEquals(studSize + 2, studRepo.getAll(null).size());
		
		
	}
	
	@Test
	public void testAddAndRemoveChildren(){
		int studSize = studRepo.getAll(null).size();
		
		Student s1 = new Student("mail@nith.no");
		Student s2 = new Student("mail2@nith.no");
		Student s3 = new Student("mail3@nith.no");
		Student s4 = new Student("mail4@nith.no");
		
		studRepo.create(s1);
		studRepo.create(s2);
		studRepo.create(s3);
		studRepo.create(s4);
		
		FadderGroup group = new FadderGroup(924);
		fadderRepo.create(group);
		
		int childrenSize = fadderRepo.getById(group.getId()).getFadderChildren().size();
		//Add children to group
		group.getFadderChildren().add(s1);
		group.getFadderChildren().add(s2);
		group.getFadderChildren().add(s3);
		group.getFadderChildren().add(s4);
		fadderRepo.update(group);
		//Check if they were added to the group
		assertEquals(childrenSize + 4, fadderRepo.getById(group.getId()).getFadderChildren().size());
		
		//Remove a children:
		group.getFadderChildren().remove(s1);
		fadderRepo.update(group);
		assertEquals(childrenSize + 3, fadderRepo.getById(group.getId()).getFadderChildren().size());
		
		//Student should still exist in student db:
		assertEquals(studSize + 4, studRepo.getAll(null).size());
		
		
	}

}

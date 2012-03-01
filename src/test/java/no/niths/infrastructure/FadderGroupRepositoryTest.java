package no.niths.infrastructure;

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
		
	}
	
	//TODO: Write a proper test
//	@Test
//	public void testAddAndRemoveLeaders(){
//		
//		int studSize = studRepo.getAll(null).size();
//		
//		Student s1 = new Student("mail@nith.no");
//		Student s2 = new Student("mail2@nith.no");
//		Student s3 = new Student("mail3@nith.no");
//		Student s4 = new Student("mail4@nith.no");
//		
//		FadderGroup group = new FadderGroup(924);
//		group.getLeaders().add(s1);
//		group.getLeaders().add(s2);
//		fadderRepo.create(group);
//		
//		assertEquals(false, fadderRepo.getById(group.getId()).getLeaders().isEmpty());
//		
//		assertEquals(studSize, studRepo.getAll(null).size());
//		
//		
//		
//		
//	}

}

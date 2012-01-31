package no.niths.test.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.awt.color.CMMException;

import no.niths.common.config.HibernateConfig;
import no.niths.domain.Committee;
import no.niths.domain.CommitteeEvent;
import no.niths.domain.Student;
import no.niths.infrastructure.interfaces.CommitteeEventsRepository;
import no.niths.infrastructure.interfaces.CommitteesRepository;
import no.niths.infrastructure.interfaces.StudentRepository;
import no.niths.test.common.config.TestAppConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= { TestAppConfig.class, HibernateConfig.class})
@Transactional
public class CommitteeRepositoryTest {

	@Autowired
	private CommitteesRepository committeeRepo;
		
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private CommitteeEventsRepository eventRepo;
	
	@Test
	@Rollback(true)
	public void testCRUD() {
		Committee committee = new Committee("LUG", "Linux");

		committee.setId(committeeRepo.create(committee));
		assertEquals(committee, committeeRepo.getCommitteeById(committee.getId()));
	
		committee.setName("LINUXs");
		committeeRepo.update(committee);

		assertEquals(committee, committeeRepo.getCommitteeById(committee.getId()));
		
		committeeRepo.delete(committee);
		assertNull(committeeRepo.getCommitteeById(committee.getId()));
	}
	
	@Test
	@Rollback(true)
	public void testStudentJoin(){
		Committee committee = new Committee("LUG", "Linux");
		
		Student stud1 = new Student	("JJ.s","doe");
		Student stud2 = new Student("Foo","bar");
		studentRepo.create(stud1);
		studentRepo.create(stud2);
		
		committee.getStudents().add(stud1);
		committee.getStudents().add(stud2);
		
		committeeRepo.create(committee);
						
		committee = committeeRepo.getCommitteeByIdWithStudents(committee.getId());
		
		assertEquals(2, committee.getStudents().size());
		
		committee.getStudents().clear();
		committeeRepo.update(committee);
		
		
		studentRepo.delete(stud1);
		studentRepo.delete(stud2);
		
		assertEquals(0, studentRepo.getAllStudents().size());
		committeeRepo.delete(committee);
		
		
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
	
	@Test
	@Rollback(true)
	public void committeeWithStudentAndEventsTest(){
		CommitteeEvent event = new CommitteeEvent();
		Student stud1 = new Student	("JJ.s","doe");
		Committee committee = new Committee("LUG", "Linux");
		committee.getEvents().add(event);
		committee.getStudents().add(stud1);
		committeeRepo.create(committee);
		
		Committee temp = committeeRepo.getCommitteeById(committee.getId());
		
		assertEquals(1, temp.getEvents().size());
		assertEquals(1, temp.getStudents().size());
	}
	


}

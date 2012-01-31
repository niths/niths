package no.niths.test.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import no.niths.common.config.AppConfig;
import no.niths.domain.Committee;
import no.niths.domain.Student;
import no.niths.infrastructure.interfaces.CommitteesRepository;
import no.niths.infrastructure.interfaces.StudentRepository;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes ={ AppConfig.class })
public class CommitteeRepositoryTest {

	@Autowired
	private CommitteesRepository committeeRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Test
	public void testCRUD() {
		Committee committee = new Committee("LUG", "Linux");

		committee.setId(committeeRepo.create(committee));
		assertEquals(committee, committeeRepo.getCommitteeById(committee.getId()));
	
		committee.setName("LINUXXx");
		committeeRepo.update(committee);

		assertEquals(committee, committeeRepo.getCommitteeById(committee.getId()));
		
		committeeRepo.delete(committee);
		assertNull(committeeRepo.getCommitteeById(committee.getId()));
	}
	
	@Test
	public void testStudentJoin(){
		Committee committee = new Committee("LUG", "Linux");
		
		Student stud1 = new Student	("JJ.s","doe");
		Student stud2 = new Student("Foo","bar");
		studentRepo.create(stud1);
		studentRepo.create(stud2);
		
		committee.getStudents().add(stud1);
		committee.getStudents().add(stud2);
		
		committeeRepo.create(committee);
						
		committee = committeeRepo.getCommitteeById(committee.getId());
		
		assertEquals(2, committee.getStudents().size());
		
//		committee.getStudents().clear();
//		getRepo().update(committee);
		
		
//		getStudentRepo().delete(stud1);
//		getStudentRepo().delete(stud2);
//		getRepo().delete(committee);
	}

	@Test
	public void testEventJoin(){
		assertNull(null);
	}
	


}

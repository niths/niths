package no.niths.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Student;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class }, loader = AnnotationConfigContextLoader.class)
public class StudentServiceTest {

	private static final Logger logger = LoggerFactory
			.getLogger(StudentServiceTest.class);
	
	@Autowired
	private StudentService studService;
	
	@Test
	@Rollback(true)
	public void testCRUD(){
		
		assertEquals(true, studService.getAllStudents().isEmpty());
		
		//Testing create
		Student s = new Student("John", "Doe");		
		Student x = new Student("Vera", "Fine");		
		studService.createStudent(s);
		studService.createStudent(x);
		
		//Testing get
		assertEquals(2, studService.getAllStudents().size());
		assertEquals(s, studService.getStudentById(s.getId()));
		
		//Testing update
		s.setEmail("john@doe.com");
		
		assertEquals(null, studService.getStudentById(s.getId()).getEmail());
		
		studService.updateStudent(s);
		assertEquals("john@doe.com", studService.getStudentById(s.getId()).getEmail());
		
		
		//Testing delete
		studService.deleteStudent(s.getId());
		assertEquals(1, studService.getAllStudents().size());
		
		studService.deleteStudent(x.getId());
		assertEquals(true, studService.getAllStudents().isEmpty());
		
	}

}

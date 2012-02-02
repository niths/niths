package no.niths.services;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Student;
import no.niths.infrastructure.interfaces.StudentRepository;
import no.niths.services.StudentService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class }, loader = AnnotationConfigContextLoader.class)
public class StudentServiceTest {

	private static final Logger logger = LoggerFactory
			.getLogger(StudentServiceTest.class);
	
	private RestTemplate template;
	private String getAllStudents = "http://localhost:8080/niths/students/";
	
//	@Autowired
//	private StudentRepository studRep;
	
	@Autowired
	private no.niths.services.StudentService studService;
	
//	@Before
//	public void init(){
//		template = new RestTemplate();
//	}
	
	@Test
	@Rollback(true)
	public void testGetByID(){
		Student s = new Student("John", "Doe");
		
		//studRep.create(s);
		
		studService.createStudent(s);
		
		logger.debug("DDDDDD : " + s.getId());
		
//		logger.debug(" ID. :::::::." + s.getId());
//		String responseString = template.getForObject(getAllStudents + s.getId() + ".json", String.class);
//		
//		logger.debug(responseString);
	}

}

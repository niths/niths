package no.niths.application.rest;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Student;
import no.niths.infrastructure.interfaces.StudentRepository;

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
public class StudentControllerTest {

	private static final Logger logger = LoggerFactory
			.getLogger(StudentControllerTest.class);
	
	private RestTemplate template;
	private String getAllStudents = "http://localhost:8080/niths/students/";
	
	@Autowired
	StudentRepository studRep;
	
	@Before
	public void init(){
		template = new RestTemplate();
	}
	
	@Test
	@Rollback(true)
	public void testGetByID(){
		Student s = new Student("John", "Doe");
		studRep.create(s);
		logger.debug(" ID. :::::::." + s.getId());
		String responseString = new RestTemplate().getForObject(getAllStudents + s.getId() + ".json", String.class);
		
		logger.debug(responseString);
	}

}

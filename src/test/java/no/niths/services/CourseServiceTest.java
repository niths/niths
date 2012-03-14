package no.niths.services;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.APIEvent;
import no.niths.domain.Course;
import no.niths.domain.Subject;
import no.niths.services.interfaces.APIEventService;
import no.niths.services.interfaces.CourseService;
import no.niths.services.interfaces.SubjectService;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class CourseServiceTest {

	private static final Logger logger = LoggerFactory
			.getLogger(CourseServiceTest.class);

	@Autowired
	private CourseService cService;
	
	@Autowired
	private SubjectService sService;
	
	@Test
	public void testCrud(){
		int size = cService.getAll(null).size();
		Course c1 = new Course();
		cService.create(c1);
		assertEquals(size + 1, cService.getAll(null).size());
		
		c1.setName("The name is this");
		cService.update(c1);
		
		assertEquals("The name is this", cService.getById(c1.getId()).getName());
		
		Course temp = new Course();
		temp.setName("XXXsssXXX");
		assertEquals(true, cService.getAll(temp).isEmpty());
		temp.setName("The name is this");
		assertEquals(1, cService.getAll(temp).size());
		
		cService.hibernateDelete(c1.getId());
		
	}
	
	@Test
	public void testSubjectRelations(){
		int subSize = sService.getAll(null).size();
		Course c1 = new Course();
		cService.create(c1);
		
		Subject s1 = new Subject();
		Subject s2 = new Subject();
		Subject s3 = new Subject();
		sService.create(s1);
		sService.create(s2);
		sService.create(s3);
		assertEquals(subSize + 3, sService.getAll(null).size());
		
		Course temp = cService.getById(c1.getId());
		temp.getSubjects().add(s1);
		temp.getSubjects().add(s2);
		temp.getSubjects().add(s3);
		cService.update(temp);
		
		assertEquals(3, cService.getById(c1.getId()).getSubjects().size());
		
		//Remove a subject
		temp = cService.getById(c1.getId());
		temp.getSubjects().remove(s1);
		cService.update(temp);
		assertEquals(2, cService.getById(temp.getId()).getSubjects().size());
		
		//Subject should still exist
		assertEquals(subSize + 3, sService.getAll(null).size());
		//Delete a subject
		sService.hibernateDelete(s2.getId());
		assertEquals(subSize + 2, sService.getAll(null).size());
		
		temp = cService.getById(c1.getId());
		assertEquals(1, cService.getById(temp.getId()).getSubjects().size());
		
		cService.hibernateDelete(c1.getId());
		assertEquals(subSize + 2, sService.getAll(null).size());
		
		sService.hibernateDelete(s1.getId());
		sService.hibernateDelete(s3.getId());
		
		
	}
	
}

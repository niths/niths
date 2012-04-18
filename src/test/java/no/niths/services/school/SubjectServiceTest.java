package no.niths.services.school;

import static org.junit.Assert.assertEquals;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.school.Student;
import no.niths.domain.school.Subject;
import no.niths.services.school.interfaces.StudentService;
import no.niths.services.school.interfaces.SubjectService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class SubjectServiceTest {

	@Autowired
	private SubjectService service;
	@Autowired
	private StudentService sservice;
	
	@Test
	public void testCRUD(){
		
		int size = service.getAll(null).size();
		
		Subject s1 = new Subject();
		s1.setName("the name xxx");
		service.create(s1);
		assertEquals(size + 1, service.getAll(null).size());
		
		Subject temp = service.getById(s1.getId());
		assertEquals("the name xxx", temp.getName());
		
		temp.setName("the name yyy");
		service.update(temp);
		
		temp = service.getById(s1.getId());
		assertEquals("the name yyy", temp.getName());
		
		temp = new Subject();
		temp.setName("the name yyy");
		
		assertEquals(1, service.getAll(temp).size());
		
		service.hibernateDelete(s1.getId());
		assertEquals(size, service.getAll(null).size());
		
	}
	
	@Test
	public void testTutorRela(){
		Student s1 = new Student("ben@nith.com");
		Student s2 = new Student("be2n@nith.com");
		
		sservice.create(s1);
		sservice.create(s2);
		
		Subject sub = new Subject("pg211");
		
		service.create(sub);
		
		sub.getTutors().add(s1);
		sub.getTutors().add(s2);
		
		service.update(sub);
		
		Subject temp = service.getById(sub.getId());
		
		assertEquals(2, temp.getTutors().size());
		
		service.hibernateDelete(sub.getId());
		sservice.hibernateDelete(s1.getId());
		sservice.hibernateDelete(s2.getId());
	}
	
}

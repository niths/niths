package no.niths.services;

import static org.junit.Assert.assertEquals;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Subject;
import no.niths.services.interfaces.SubjectService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class SubjectServiceTest {

	private static final Logger logger = LoggerFactory
			.getLogger(SubjectServiceTest.class);
	
	@Autowired
	private SubjectService service;
	
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
	
}

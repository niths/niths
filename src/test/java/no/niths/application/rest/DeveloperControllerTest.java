package no.niths.application.rest;


import static org.junit.Assert.assertEquals;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.DeveloperController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Developer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class DeveloperControllerTest {

	@Autowired 
	private DeveloperController controller;
	
	@Test
	public void testController(){
		int size = 0;
		try{
			size = controller.getAll(new Developer()).size();
		}catch(ObjectNotFoundException e){}
		
		
		Developer dev = new Developer("Name");
		dev.setEmail("mail@mail.com");
		controller.create(dev);
		
		assertEquals(size + 1, controller.getAll(new Developer()).size());
		Developer get = controller.getById(dev.getId());
		assertEquals(get, dev);
		assertEquals(null, get.getEnabled());
		
		get.setEnabled(true);
		controller.enableDeveloper(get.getId());
		
		get = controller.getById(dev.getId());
		assertEquals(get, dev);
		assertEquals(true, get.getEnabled());
		
		controller.hibernateDelete(get.getId());
		
	}
}

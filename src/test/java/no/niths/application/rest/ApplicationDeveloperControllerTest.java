package no.niths.application.rest;


import static org.junit.Assert.assertEquals;

import java.util.List;

import no.niths.application.rest.development.interfaces.ApplicationController;
import no.niths.application.rest.development.interfaces.DeveloperController;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.development.Application;
import no.niths.domain.development.Developer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class ApplicationDeveloperControllerTest {

    private MockHttpServletResponse res;

	@Autowired 
	private DeveloperController controller;
	
	@Autowired
	private ApplicationController aController;

	@Before
	public void setUp() {
	    res = new MockHttpServletResponse();
	}

	@Test
	public void getTopApps(){
		Application a1 = new Application("application xzy1");
		Application a2 = new Application("application xxs2");
		Application a3 = new Application("application xsy3");
		a1.setRequests(new Long(10));
		a2.setRequests(new Long(20));
		a3.setRequests(new Long(5));
		aController.create(a1, res);
		aController.create(a2, res);
		aController.create(a3, res);
		
		List<Application> all = aController.getTopApps(5);
		assertEquals(all.get(0), a2);
		assertEquals(all.get(1), a1);
		assertEquals(all.get(2), a3);
		
		aController.delete(a1.getId());
		aController.delete(a2.getId());
		aController.delete(a3.getId());
	}
	
	@Test
	public void addApp(){
		int size = 0;
		try{
			size = controller.getAll(new Developer()).size();
		}catch(ObjectNotFoundException e){}
		
		Developer dev = new Developer("Per", "person@nith.no");
		controller.create(dev, res);
		assertEquals(size + 1, controller.getAll(new Developer()).size());
		
		Application app = new Application("The title");
		aController.create(app, res);
		
		Developer fetched = controller.getById(dev.getId());
		Application aFetched = aController.getById(app.getId());
		assertEquals(true, fetched.getApps().isEmpty());
		controller.addApplication(fetched.getId(), aFetched.getId());
		fetched = controller.getById(dev.getId());
		assertEquals(1, fetched.getApps().size());
		
		controller.removeApplication(fetched.getId(), aFetched.getId());
		fetched = controller.getById(dev.getId());
		assertEquals(true, fetched.getApps().isEmpty());
	}

	@Test
	public void testController(){
		int size = 0;
		try{
			size = controller.getAll(new Developer()).size();
		}catch(ObjectNotFoundException e){}
		
		
		Developer dev = new Developer();
		dev.setEmail("mail@mail.com");
		dev.setName("DevName");
		controller.create(dev, res);
		
		assertEquals(size + 1, controller.getAll(new Developer()).size());
		Developer get = controller.getById(dev.getId());
		assertEquals(get, dev);
		assertEquals(null, get.getEnabled());
		
		get.setEnabled(true);
		controller.enableDeveloper(get.getId());
		
		get = controller.getById(dev.getId());
		assertEquals(get, dev);
		assertEquals(true, get.getEnabled());
		
		controller.delete(get.getId());
		
	}
}

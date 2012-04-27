package no.niths.services;

import static org.junit.Assert.assertEquals;

import java.util.List;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.developing.Application;
import no.niths.domain.developing.Developer;
import no.niths.services.developing.interfaces.ApplicationService;
import no.niths.services.developing.interfaces.DeveloperService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class ApplicationServiceTest {

	@Autowired
	private ApplicationService service;
	
	@Autowired
	private DeveloperService devService;
	
	@Test
	public void testGetTopApps(){
		Application a1 = new Application("application xzy1");
		Application a2 = new Application("application xxs2");
		Application a3 = new Application("application xsy3");
		a1.setRequests(new Long(10));
		a2.setRequests(new Long(20));
		a3.setRequests(new Long(5));
		service.create(a1);
		service.create(a2);
		service.create(a3);
		
		List<Application> all = service.getTopApps(5);
		assertEquals(all.get(0), a2);
		assertEquals(all.get(1), a1);
		assertEquals(all.get(2), a3);
		
		service.hibernateDelete(a1.getId());
		service.hibernateDelete(a2.getId());
		service.hibernateDelete(a3.getId());
	}
	
	@Test
	public void testCRUD(){
		
		int appSize = service.getAll(null).size();
		
		Application a1 = new Application();
		a1.setTitle("this a title ok");
		Application a2 = new Application();
		a2.setTitle("Title is title");
		service.create(a1);
		service.create(a2);
		assertEquals(appSize + 2, service.getAll(null).size());
		
		Application temp = new Application();
		temp.setTitle("this a title ok");
		assertEquals(1, service.getAll(temp).size());
		
		Application fetched = service.getById(a1.getId());
		assertEquals(a1, fetched);
		
		a1.setTitle("this is another title");
		service.update(a1);
		assertEquals(0, service.getAll(temp).size());
		assertEquals(1, service.getAll(a1).size());
		
		service.hibernateDelete(a1.getId());
		service.hibernateDelete(a2.getId());
	}
	
	@Test
	public void testDevRelation(){
		int devSize = devService.getAll(null).size();
		int appSize = service.getAll(null).size();
		
		Developer d1 = new Developer("Navnet paa");
		d1.setEmail("email@mailer.com");
		devService.create(d1);
		assertEquals(devSize + 1, devService.getAll(null).size());

		Application a1 = new Application("Heiland");
		Application a2 = new Application("Segkland");
		service.create(a1);
		service.create(a2);
		assertEquals(appSize + 2, service.getAll(null).size());
		assertEquals(true, devService.getById(d1.getId()).getApps().isEmpty());
		//Set developer from app
		a1.setDeveloper(d1);
		service.update(a1);
		//Dev should now have the app
		assertEquals(1, devService.getById(d1.getId()).getApps().size());
		
		service.hibernateDelete(a1.getId());
		assertEquals(appSize + 1, service.getAll(null).size());
		assertEquals(true, devService.getById(d1.getId()).getApps().isEmpty());
		
		devService.hibernateDelete(d1.getId());
		service.hibernateDelete(a2.getId());
	}
	
}

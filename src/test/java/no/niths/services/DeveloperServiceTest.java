package no.niths.services;

import static org.junit.Assert.assertEquals;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Application;
import no.niths.domain.Developer;
import no.niths.services.interfaces.ApplicationService;
import no.niths.services.interfaces.DeveloperService;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class DeveloperServiceTest {

	@Autowired
	private DeveloperService devService;
	
	@Autowired
	private ApplicationService appService;

	@Test
	public void testCRUD() {
		int devSize = devService.getAll(null).size();
		int appSize = appService.getAll(null).size();
		
		Developer dev = new Developer("Jhon");
		devService.create(dev);
		assertEquals(devSize + 1, devService.getAll(null).size());
		
		//create an app and set the developer
		Application app = new Application("Bird",null,null,null);
		app.setDeveloper(dev);
		appService.create(app);
		Application app2 = new Application("Birds",null,null,null);
		app2.setDeveloper(dev);
		appService.create(app2);
		
		assertEquals(appSize + 2, appService.getAll(null).size());
		
		Developer fetched = devService.getById(dev.getId());
		assertEquals(2, fetched.getApps().size());
		//Delete an app
		appService.hibernateDelete(app.getId());
		assertEquals(appSize + 1, appService.getAll(null).size());
		
		//App should be removed from dev
		fetched = devService.getById(dev.getId());
		assertEquals(1, fetched.getApps().size());
		
//		for (Application a : fetched.getApps()){
//			appService.hibernateDelete(a.getId());			
//		}
//		//Delete dev
		devService.hibernateDelete(dev.getId());
		assertEquals(devSize, devService.getAll(null).size());
		//App should still exist
		assertEquals(appSize + 1, appService.getAll(null).size());
		
		appService.hibernateDelete(app2.getId());
		assertEquals(0, appService.getAll(null).size());
	}
	

	@Test
	public void testAppDevRelation(){
		Developer d1 = new Developer("mrDoe");
		devService.create(d1);
		Application a1 = new Application("App",null,null,null);
		Application a2 = new Application("Apssp",null,null,null);
		appService.create(a1);
		appService.create(a2);
		
		Developer temp = devService.getById(d1.getId());
		temp.getApps().add(a1);
		temp.getApps().add(a2);
		devService.updateForDeveloperController(temp);
		//Did developer get apps?
		temp = devService.getById(d1.getId());
		assertEquals(2, temp.getApps().size());
		//Did app get developer?
		Application appTemp = appService.getById(a1.getId());
		assertEquals(temp, appTemp.getDeveloper());
		
		//Remove dev from app
		appTemp.setDeveloper(new Developer());
		appService.update(appTemp);
		//App should be removed from dev
		temp = devService.getById(d1.getId());
		assertEquals(1, temp.getApps().size());
		//Set it back
		appTemp.setDeveloper(temp);
		appService.update(appTemp);
		//Dev should get the app back
		temp = devService.getById(d1.getId());
		assertEquals(2, temp.getApps().size());
		
		//Delete dev
		devService.hibernateDelete(temp.getId());
		//Apps should still be there but with null dev
		appTemp = appService.getById(a1.getId());
		assertEquals(null, appTemp.getDeveloper());
		
		appService.hibernateDelete(a1.getId());
		appService.hibernateDelete(a2.getId());
	}

}

package no.niths.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Application;
import no.niths.domain.Developer;
import no.niths.domain.Student;
import no.niths.infrastructure.interfaces.DeveloperRepository;
import no.niths.services.interfaces.ApplicationService;
import no.niths.services.interfaces.DeveloperService;
import no.niths.services.interfaces.StudentService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class DeveloperServiceTest {

	private static final Logger logger = LoggerFactory
			.getLogger(DeveloperServiceTest.class);

	@Autowired
	private DeveloperService devService;
	
	@Autowired
	private ApplicationService appService;

	@Test
	@Rollback(true)
	public void testCRUD() {
		int devSize = devService.getAll(null).size();
		int appSize = appService.getAll(null).size();
		
		Developer dev = new Developer();
		devService.create(dev);
		assertEquals(devSize + 1, devService.getAll(null).size());
		
		//create an app and set the developer
		Application app = new Application();
		app.setDeveloper(dev);
		appService.create(app);
		Application app2 = new Application();
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
		
		//TODO: FIX relationship
//		//Delete dev, apps should also be deleted
//		devService.hibernateDelete(dev.getId());
//		assertEquals(devSize, devService.getAll(null).size());
//		assertEquals(appSize, appService.getAll(null).size());
	}

}

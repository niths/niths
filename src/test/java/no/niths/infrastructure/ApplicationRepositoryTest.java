package no.niths.infrastructure;

import static org.junit.Assert.assertEquals;

import java.util.List;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.developing.Application;
import no.niths.infrastructure.developing.interfaces.ApplicationRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= { TestAppConfig.class, HibernateConfig.class})
@Transactional 
public class ApplicationRepositoryTest {
	
	@Autowired
	private ApplicationRepository appRepo;
	
	@Test
	public void testCRUD(){
		int size = appRepo.getAll(null).size();
		
		Application a = new Application("app");
		appRepo.create(a);
		
		assertEquals(size + 1, appRepo.getAll(null).size());
		
		assertEquals(a, appRepo.getById(a.getId()));
		
		a.setTitle("title");
		appRepo.update(a);
		assertEquals("title", appRepo.getById(a.getId()).getTitle());
		
		assertEquals(true, appRepo.delete(a.getId()));
	}
	
	@Test
	public void getTopApps(){
		Application a1 = new Application("application xzy");
		Application a2 = new Application("application xxs");
		Application a3 = new Application("application xsy");
		a1.setRequests(new Long(10));
		a2.setRequests(new Long(20));
		a3.setRequests(new Long(5));
		appRepo.create(a1);
		appRepo.create(a2);
		appRepo.create(a3);
		
		List<Application> all = appRepo.getTopApps(5);
		assertEquals(all.get(0), a2);
		assertEquals(all.get(1), a1);
		assertEquals(all.get(2), a3);
	}

}

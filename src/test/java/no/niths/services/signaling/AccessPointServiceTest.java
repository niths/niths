package no.niths.services.signaling;

import static org.junit.Assert.assertEquals;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.signaling.AccessPoint;
import no.niths.services.signaling.interfaces.AccessPointService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class AccessPointServiceTest {

	@Autowired
	private AccessPointService service;

	
	@Test
	public void testCRUD(){
		
		int size = service.getAll(null).size();
		
		AccessPoint ap = new AccessPoint();
		ap.setAddress("00:23:12:ad:af:ad");
		service.create(ap);
		assertEquals(size + 1, service.getAll(null).size());
		
		AccessPoint temp = service.getById(ap.getId());
		assertEquals("00:23:12:ad:af:ad", temp.getAddress());
		
		temp.setAddress("00:23:12:ad:23:33");
		service.update(temp);
		
		temp = service.getById(ap.getId());
		assertEquals("00:23:12:ad:23:33", temp.getAddress());
		
		temp = new AccessPoint();
		temp.setAddress("00:23:12:ad:23:33");
		
		assertEquals(1, service.getAll(temp).size());
		
		service.hibernateDelete(ap.getId());
		assertEquals(size, service.getAll(null).size());
		
	}
}

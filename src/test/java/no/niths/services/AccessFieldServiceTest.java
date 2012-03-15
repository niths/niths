package no.niths.services;

import static org.junit.Assert.assertEquals;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.signaling.AccessField;
import no.niths.services.interfaces.AccessFieldService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class AccessFieldServiceTest {

	@Autowired
	private AccessFieldService service;

	@Test
	public void testCRUD(){
		
		int size = service.getAll(null).size();
		
		AccessField ap = new AccessField();
		ap.setMinRange(10);
		ap.setMaxRange(40);
		service.create(ap);
		assertEquals(size + 1, service.getAll(null).size());
		
		AccessField temp = service.getById(ap.getId());
		int actual= temp.getMinRange();
		assertEquals(10, actual);
		
		actual= temp.getMaxRange();
		assertEquals(40, actual);
		
		temp.setMinRange(20);
		temp.setMaxRange(50);
		service.update(temp);
		
		temp = service.getById(ap.getId());
		
		actual= temp.getMinRange();
		assertEquals(20, actual);
		
		actual= temp.getMaxRange();
		assertEquals(50, actual);
		
		temp = new AccessField();
		temp.setMaxRange(50);
		
		assertEquals(1, service.getAll(temp).size());
		
		service.hibernateDelete(ap.getId());
		assertEquals(size, service.getAll(null).size());
		
	}
}

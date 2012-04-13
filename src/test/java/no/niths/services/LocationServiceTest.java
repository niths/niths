package no.niths.services;

import static org.junit.Assert.assertEquals;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.location.Location;
import no.niths.services.location.interfaces.LocationService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class LocationServiceTest {

	@Autowired
	private LocationService service;

	@Test
	public void testCRUD() {
		// create
		int size = service.getAll(null).size();
		
		Location loc = new Location("Oslo",10.2304,90.2030);
	
		service.create(loc);
		assertEquals(size + 1, service.getAll(null).size());
		assertEquals(loc, service.getById(loc.getId()));


		loc.setPlace("Molde");
		service.update(loc);
		loc = service.getById(loc.getId());
		assertEquals("Molde", loc.getPlace());
	

		service.delete(loc.getId());
		assertEquals(size, service.getAll(null).size());
	}
	

}

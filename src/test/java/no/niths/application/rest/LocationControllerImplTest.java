package no.niths.application.rest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.location.interfaces.LocationController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.location.Location;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class LocationControllerImplTest {

    private MockHttpServletResponse res;

	@Autowired
	private LocationController controller;

	private Location testLocation01;
	private Location testLocation02;
	private Location testLocation03;
	private Location testLocation04;

	@Before
	public void setUp() throws Exception {
	    res = new MockHttpServletResponse();
		testLocation01 = new Location("Oslo", 90.2030, 20.2341);
		testLocation02 = new Location("Stavanger", 10.230, 10.2326);
		testLocation03 = new Location("Sandefjord", 10.2030, 10.2347);
		testLocation04 = new Location("Haugesund", 10.2030, 11.4341);

		controller.create(testLocation01, res);
		controller.create(testLocation02, res);
		controller.create(testLocation03, res);
		controller.create(testLocation04, res);
	}

	@After
	public void tearDown() throws Exception {
		controller.hibernateDelete(testLocation01.getId());
		controller.hibernateDelete(testLocation02.getId());
		controller.hibernateDelete(testLocation03.getId());
		controller.hibernateDelete(testLocation04.getId());
	}

	@Test
	public void testGetById() {
		Location loc = controller.getById(testLocation01.getId());
		assertEquals(testLocation01, loc);
	}

	@Test
	public void testGetAllT() {
		List<Location> locs = controller.getAll(new Location());
		assertEquals(4, locs.size());
	}

	@Test
	public void testGetAllWithParams() {
		Location loc = new Location();
		loc.setLongitude(10.2030);
		List<Location> locs = controller.getAll(loc);
		assertEquals(2, locs.size());
	}

	@Test(expected = ObjectNotFoundException.class)
	public void testGetAllWithNotExistingParams() {
		Location loc = new Location();
		loc.setPlace("asdhjasdh");
		controller.getAll(loc);

	}

	@Test
	public void testGetAllTIntInt() {
		List<Location> locs = controller.getAll(new Location(), 0, 2);
		assertEquals(2, locs.size());
	}

	@Test
	public void testUpdate() {
		Location loc = new Location();
		loc.setId(testLocation03.getId());
		loc.setLatitude(20.2000);
		controller.update(loc);
		assertEquals(loc.getLatitude(),
				controller.getById(testLocation03.getId()).getLatitude());
	}
}
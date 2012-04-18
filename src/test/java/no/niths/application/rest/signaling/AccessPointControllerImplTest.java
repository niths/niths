package no.niths.application.rest.signaling;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import no.niths.application.rest.signaling.interfaces.AccessPointController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.signaling.AccessPoint;

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
public class AccessPointControllerImplTest {

    private MockHttpServletResponse res;

	@Autowired
	private AccessPointController controller;
	
	private AccessPoint testAP01;
	private AccessPoint testAP02;
	private AccessPoint testAP03;
	private AccessPoint testAP04;
	
	@Before
	public  void setUp() throws Exception {
	    res = new MockHttpServletResponse();

		testAP01 = new AccessPoint("00:22:AB:CF:FF");
		testAP02 = new AccessPoint("00:23:AB:CF:FF");
		testAP03 = new AccessPoint("00:24:AD:CF:AF");
		testAP04 = new AccessPoint("00:25:AB:AF:FF");
		
		controller.create(testAP01, res);
		controller.create(testAP02, res);
		controller.create(testAP03, res);
		controller.create(testAP04, res);
	}

	@After
	public void tearDown() throws Exception {		
		controller.delete(testAP01.getId());
		controller.delete(testAP02.getId());
		controller.delete(testAP03.getId());
		controller.delete(testAP04.getId());
	}

	@Test
	public void testGetAllT() {
		ArrayList<AccessPoint> aps = controller.getAll(null);
		assertEquals(4,aps.size());
	}

	@Test
	public void testGetAllTIntInt() {
		ArrayList<AccessPoint> aps = controller.getAll(null,1,2);
		assertEquals(2,aps.size());
	}
	
	@Test
	public void testGetById() {
		AccessPoint ap = controller.getById(testAP03.getId());
		assertEquals(testAP03, ap);
	}

	@Test
	public void testUpdate() {
		AccessPoint changedAP = new AccessPoint();
		changedAP.setAddress("22:1A:AA:FF:00");
		changedAP.setId(testAP04.getId());
		
		controller.update(changedAP);
		assertEquals(changedAP.getAddress(),  controller.getById(testAP04.getId()).getAddress());
	}
}

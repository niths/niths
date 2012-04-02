package no.niths.application.rest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import no.niths.application.rest.exception.DuplicateEntryCollectionException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.AccessFieldController;
import no.niths.application.rest.interfaces.AccessPointController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.signaling.AccessField;
import no.niths.domain.signaling.AccessPoint;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class AccessFieldControllerImplTest {

	@Autowired
	private AccessFieldController controller;
	
	@Autowired
	private AccessPointController apController;
	
	private AccessPoint testAP;
	private AccessField testAF01;
	private AccessField testAF02;
	private AccessField testAF03;
	private AccessField testAF04;
	
	@Before
	public void setUp() throws Exception {
		testAF01 = new AccessField(22,50);
		testAF02 = new AccessField(22,50);
		testAF03 = new AccessField(22,50);
		testAF04 = new AccessField(22,50);
		
		testAP = new AccessPoint("22:1A:AA:FF:00");
		apController.create(testAP);
		
		controller.create(testAF01);
		controller.create(testAF02);
		controller.create(testAF03);
		controller.create(testAF04);
	}

	@After
	public void tearDown() throws Exception {
		controller.hibernateDelete(testAF01.getId());
		controller.hibernateDelete(testAF02.getId());
		controller.hibernateDelete(testAF03.getId());
		controller.hibernateDelete(testAF04.getId());
	
		apController.hibernateDelete(testAP.getId());
	}

	@Test
	public void testUpdateAccessField() {
		AccessField af = new AccessField();
		af.setId(testAF04.getId());
		af.setMaxRange(66);
		controller.update(af);
		assertEquals(af.getMaxRange(), controller.getById(testAF04.getId()).getMaxRange());
	}

	@Test
	public void testGetById() {
		AccessField af = controller.getById(testAF03.getId());
		assertEquals(testAF03,af);
	}

	@Test
	public void testGetAllT() {
		ArrayList<AccessField> afs = controller.getAll(null);
		assertEquals(4,afs.size());
	}

	@Test
	public void testGetAllTIntInt() {
		ArrayList<AccessField> aps = controller.getAll(null,2,3);
		assertEquals(2,aps.size());
	}

	@Test 
	public void testAddAndRemoveAccessPoint(){
		AccessField af = controller.getById(testAF02.getId());
		assertEquals(null, af.getAccessPoint());
		
		// add access point to access field
		controller.addAccessPoint(testAF02.getId(), testAP.getId());
		af = controller.getById(testAF02.getId());
		assertEquals(testAP, af.getAccessPoint());
		
		// remove access point from access field
		controller.removeAccessPoint(testAF02.getId(), testAP.getId());
		
		// access field should not have access point
		af = controller.getById(testAF02.getId());
		assertEquals(null, af.getAccessPoint());
		
	}
	
	@Test(expected=DuplicateEntryCollectionException.class)
	public void testAddTheSameAccessPointTwice_shouldThrowException(){
		controller.addAccessPoint(testAF02.getId(), testAP.getId());
		controller.addAccessPoint(testAF02.getId(), testAP.getId());
	}
	
	@Test(expected=ObjectNotFoundException.class)
	public void testRemoveANonExistingRelationship_shouldThrowException(){
		controller.removeAccessPoint(testAF01.getId(), testAP.getId());
	}
}
package no.niths.application.rest.location;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import no.niths.application.rest.exception.ObjectInCollectionException;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.location.interfaces.RoomController;
import no.niths.application.rest.signaling.interfaces.AccessFieldController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.location.Room;
import no.niths.domain.signaling.AccessField;

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
public class RoomControllerImplTest {

    private MockHttpServletResponse res;

	@Autowired
	private RoomController controller;

	@Autowired
	private AccessFieldController afController;

	private AccessField testAF;

	private Room testRoom01;
	private Room testRoom02;
	private Room testRoom03;
	private Room testRoom04;

	@Before
	public void setUp() throws Exception {
	    res = new MockHttpServletResponse();
		testRoom01 = new Room("81");
		testRoom02 = new Room("40");
		testRoom03 = new Room("Kantina");
		testRoom04 = new Room("41");
		testAF = new AccessField(22, 50);

		controller.create(testRoom01, res);
		controller.create(testRoom02, res);
		controller.create(testRoom03, res);
		controller.create(testRoom04, res);
		afController.create(testAF, res);

	}

	@After
	public void tearDown() throws Exception {
		controller.delete(testRoom01.getId());
		controller.delete(testRoom02.getId());
		controller.delete(testRoom03.getId());
		controller.delete(testRoom04.getId());

		afController.delete(testAF.getId());
	}

	@Test
	public void testGetById() {
		Room room = controller.getById(testRoom02.getId());
		assertEquals(testRoom02, room);
	}

	@Test(expected = ObjectNotFoundException.class)
	public void testGetByInvalidId_shouldThrowException() {
		controller.getById(new Long(-1));
	}

	@Test
	public void testGetAllT() {
		ArrayList<Room> rooms = controller.getAll(new Room());
		assertEquals(4, rooms.size());
	}

	
	@Test
    public void testGetAllWithSearch() {
        ArrayList<Room> rooms = controller.getAll(new Room("Kantina"));
        assertEquals(1, rooms.size());
    }

	@Test
	public void testGetAllTIntInt() {
		ArrayList<Room> rooms = controller.getAll(null, 1, 3);
		assertEquals(3, rooms.size());
	}

	@Test
	public void testUpdate() {
		Room room = new Room();
		room.setId(testRoom03.getId());
		room.setRoomName("new room name");

		controller.update(room);
		assertEquals(room.getRoomName(), controller.getById(testRoom03.getId())
				.getRoomName());
	}

	@Test
	public void testAddAndRemoveAccessField(){
		Room room = controller.getById(testRoom04.getId());
		assertEquals(0, room.getAccessFields().size());
		
		// add access point to access field
		controller.addAccessField(testRoom04.getId(), testAF.getId());
		room = controller.getById(testRoom04.getId());
		assertEquals(testAF, room.getAccessFields().get(0));
		
		// remove access point from access field
		controller.removeAccessField(testRoom04.getId(), testAF.getId());
		
		// access field should not have access point
		room = controller.getById(testRoom04.getId());
		assertEquals(0, room.getAccessFields().size());
	}
	
	@Test(expected=ObjectInCollectionException.class)
	public void testAddTheSameAccessFieldTwice(){
		controller.addAccessField(testRoom04.getId(), testAF.getId());
		controller.addAccessField(testRoom04.getId(), testAF.getId());
	}
	
	@Test(expected=ObjectNotFoundException.class)
	public void testRemoveANonExistingAccessFieldRelationship(){
		controller.removeAccessField(testRoom04.getId(), testAF.getId());
	}
}

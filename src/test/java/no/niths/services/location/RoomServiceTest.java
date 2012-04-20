package no.niths.services.location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.location.Room;
import no.niths.domain.signaling.AccessField;
import no.niths.domain.signaling.AccessPoint;
import no.niths.services.location.interfaces.RoomService;
import no.niths.services.signaling.interfaces.AccessFieldService;
import no.niths.services.signaling.interfaces.AccessPointService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class RoomServiceTest {

	@Autowired
	private RoomService roomService;

	@Autowired
	private AccessPointService apService;

	@Autowired
	private AccessFieldService afService;

	@Test
	public void testCRUD() {
		int size = roomService.getAll(null).size();

		Room r = new Room();
		r.setRoomName("Vrimle");
		
		roomService.create(r);
		assertEquals(size + 1, roomService.getAll(null).size());

		Room temp = roomService.getById(r.getId());
		assertEquals("Vrimle", temp.getRoomName());

		temp.setRoomName("81");
		roomService.update(temp);

		temp = roomService.getById(r.getId());
		assertEquals("81", temp.getRoomName());

		temp = new Room();
		temp.setRoomName("81");
		assertEquals(1, roomService.getAll(temp).size());

		roomService.hibernateDelete(r.getId());
		assertEquals(size, roomService.getAll(null).size());
	}

	@Test
	public void testCascade() {
		//adding
		
		int size = roomService.getAll(null).size();
		Room r = new Room("Vrimle");
		AccessField af = new AccessField(20, 50);
		AccessPoint ap = new AccessPoint("87:82:23:24:32");
		
		apService.create(ap);
		af.setAccessPoint(ap);
		
		afService.create(af);
		r.getAccessFields().add(af);
		
		roomService.create(r);
		
		assertEquals(size + 1, roomService.getAll(null).size());

		Room temp = roomService.getById(r.getId());
		assertEquals(1, temp.getAccessFields().size());
		
		assertNotNull(temp.getAccessFields().get(0).getAccessPoint());
		
		roomService.hibernateDelete(r.getId());
		assertEquals(size, roomService.getAll(null).size());
		
		assertEquals(1, afService.getAll(null).size());
		
		afService.hibernateDelete(afService.getAll(null).get(0).getId());
		assertEquals(0, afService.getAll(null).size());
		
		assertEquals(1, apService.getAll(null).size());
		
		apService.hibernateDelete(apService.getAll(null).get(0).getId());
		assertEquals(0, apService.getAll(null).size());
	}
}

package no.niths.services;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.services.interfaces.RoomService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class RoomServiceTest {

	@Autowired
	private RoomService service;
	
	@Test
	public void testRoomService(){
		int size= service.getAll(null).size();
		System.out.println("Service :  " + size);
	}
}

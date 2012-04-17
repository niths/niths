package no.niths.infrastructure;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.infrastructure.location.interfaces.RoomRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
@Transactional
public class RoomRepositoryTest {

	@Autowired
	private RoomRepository repo;

	@Test
	public void testCRUDROOM() {
		
		
		int size = repo.getAll(null).size();
		System.out.println(size);
		

	}
}

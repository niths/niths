package no.niths.infrastructure;

import static org.junit.Assert.assertEquals;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.APIEvent;
import no.niths.infrastructure.interfaces.APIEventRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= { TestAppConfig.class, HibernateConfig.class})
@Transactional 
public class APIEventRepositoryTest {

	@Autowired
	private APIEventRepository repo;
	
	@Test
	public void testCRUD() {
		
		int size = repo.getAll(null).size();
		
		APIEvent event = new APIEvent("Title", "description");
		repo.create(event);
		
		assertEquals(size + 1, repo.getAll(null).size());
		
		event.setTitle("xxxx");		
		repo.update(event);
		
		assertEquals("xxxx", repo.getById(event.getId()).getTitle());
		
		repo.delete(event.getId());
		assertEquals(size, repo.getAll(null).size());
	}
	
}

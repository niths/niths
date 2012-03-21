package no.niths.infrastructure;

import static org.junit.Assert.assertEquals;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Feed;
import no.niths.domain.location.Location;
import no.niths.infrastructure.interfaces.FeedRepoistory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
@Transactional
public class FeedRepositoryTest {

	@Autowired
	private FeedRepoistory repo;

	@Test
	public void testCRUD() {
		// create
		int size = repo.getAll(null).size();

		Feed feed = new Feed("Hello this is a message");
		repo.create(feed);
		assertEquals(size + 1, repo.getAll(null).size());
		assertEquals(feed, repo.getById(feed.getId()));

		// update time
		feed.setMessage("new message");
		repo.update(feed);
		feed = repo.getById(feed.getId());
		assertEquals("new message", feed.getMessage());

		repo.delete(feed.getId());
		assertEquals(size, repo.getAll(null).size());

	}

	@Test 
	public void testEventLocation(){
	
		Feed feed = new Feed("Hello this is a message");
		Location loc = new Location("Oslo",10.2304,90.2030);
		feed.setLocation(loc);
		
		repo.create(feed);
		Feed temp = repo.getAll(feed).get(0);
		assertEquals(feed,temp);
		
		
		assertEquals(loc, temp.getLocation());
		
		// update 
		feed.setMessage("new message");
		temp = repo.getAll(feed).get(0);
		
		assertEquals(feed.getMessage(), temp.getMessage());
	
		assertEquals(loc, temp.getLocation());
	}
}

package no.niths.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Course;
import no.niths.domain.Topic;
import no.niths.infrastructure.interfaces.TopicsRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
@Transactional
public class TopicRepositoryTest {

	private static final Logger logger = LoggerFactory
			.getLogger(TopicRepositoryTest.class);

	private Course course = new Course("Programmering", "Programmeringsfaget");

	@Autowired
	private TopicsRepository<Topic> repo;

	@Test(expected = IllegalArgumentException.class)
	@Rollback(true)
	public void whenInsertNull_persistenceShouldFail() {
		repo.create(null);
	}

	@Test
	@Rollback(true)
	public void whenCreateTopic_topicShouldBePersisted() {
		int size = repo.getAll(null).size();

		Topic t1 = new Topic();
		t1.setTopicCode("PG111");
		repo.create(t1);

		assertEquals(size + 1, repo.getAll(null).size());
	}

	@Test
	@Rollback(true)
	public void whenGetById_TopicShouldBeReturned() {
		int size = repo.getAll(null).size();

		Topic t1 = new Topic();
		t1.setName("Java 1");
		t1.setTopicCode("PG111");
		repo.create(t1);

		assertEquals(size + 1, repo.getAll(null).size());
		
		Topic result = repo.getById(t1.getId());
		assertEquals(result, t1);
		
		result = repo.getById(new Long(999));
		assertNull(result);
	}
	@Test
	@Rollback(true)
	public void whenUpdateTopic_TopicShouldBeUpdatet() {
		int size = repo.getAll(null).size();
		
		Topic t1 = new Topic();
		t1.setName("Java 1");
		t1.setTopicCode("PG111");
		repo.create(t1);
		
		assertEquals(size + 1, repo.getAll(null).size());
		
		t1.setTopicCode("PG211");
		repo.update(t1);
		
		assertEquals("PG211", repo.getById(t1.getId()).getTopicCode());
		
	}
	@Test
	@Rollback(true)
	public void whenGetAll_allShouldBeReturnedt() {
		int size = repo.getAll(null).size();
		
		Topic t1 = new Topic();
		t1.setName("Java 1");
		repo.create(t1);
		Topic t2 = new Topic();
		t2.setName("Java 2");
		repo.create(t2);
		Topic t3 = new Topic();
		t3.setName("Java 3");
		repo.create(t3);
		
		assertEquals(size + 3, repo.getAll(null).size());
		
		assertEquals(size + 1, repo.getAll(t1).size());
		
	}
	
	

}

package no.niths.services;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.APIEvent;
import no.niths.domain.FadderGroup;
import no.niths.services.interfaces.APIEventService;
import no.niths.services.interfaces.FadderGroupService;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class FadderGroupServiceTest {

	private static final Logger logger = LoggerFactory
			.getLogger(FadderGroupServiceTest.class);

	@Autowired
	private FadderGroupService fadderService;
	
	@Test
	public void testCRUD(){
		int size = fadderService.getAll(null).size();
		FadderGroup g1 = new FadderGroup();
		g1.setGroupNumber(924);
		fadderService.create(g1);
		assertEquals(size + 1, fadderService.getAll(null).size());
		
		FadderGroup temp = new FadderGroup(924);
		assertEquals(1, fadderService.getAll(temp).size());
		
		g1.setGroupNumber(913);
		fadderService.update(g1);
		assertEquals(0, fadderService.getAll(temp).size());
		temp.setGroupNumber(913);
		assertEquals(1, fadderService.getAll(temp).size());
		
		assertEquals(g1, fadderService.getById(g1.getId()));
		
		fadderService.hibernateDelete(g1.getId());
		assertEquals(size, fadderService.getAll(null).size());
	}
	
	@Test
	public void testLeadersRelationship(){
		
	}
	
}

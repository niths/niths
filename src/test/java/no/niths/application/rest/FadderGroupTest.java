package no.niths.application.rest;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.FadderGroupController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.FadderGroup;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
@Transactional
@TransactionConfiguration(
        transactionManager = TestAppConfig.TRANSACTION_MANAGER)
public class FadderGroupTest {
	
	@Autowired
	private FadderGroupController fadderController;
	
	@Test
	public void testCRUD(){
		int size = 0;
		try{
			size = fadderController.getAll(null).size();
		}catch(ObjectNotFoundException e){
			//Do nothing
		}
		
		FadderGroup group = new FadderGroup(1337);
		
		fadderController.create(group);
		
		assertEquals(size + 1, fadderController.getAll(null).size());
		
		assertEquals(group, fadderController.getById(group.getId()));
		
		group.setGroupId(1338);
		fadderController.update(group);
		
		assertEquals(1338, fadderController.getById(group.getId()).getGroupId());
	}

}

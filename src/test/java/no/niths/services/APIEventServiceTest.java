package no.niths.services;

import static org.junit.Assert.assertEquals;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.APIEvent;
import no.niths.services.interfaces.APIEventService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class APIEventServiceTest {

	private static final Logger logger = LoggerFactory
			.getLogger(APIEventServiceTest.class);
	
	@Autowired
	private APIEventService apiService;
	
	@Test
	public void testCRUD(){
		
		int apiSize = apiService.getAll(null).size();
		
		APIEvent a1 = new APIEvent();
		APIEvent a2 = new APIEvent();
		a1.setTitle("aaaTitle");
		apiService.create(a1);
		apiService.create(a2);
		assertEquals(apiSize + 2, apiService.getAll(null).size());
		
		assertEquals(a1, apiService.getById(a1.getId()));
		
		a1.setTitle("bbbTitle");
		apiService.update(a1);
		assertEquals(a1.getTitle(), apiService.getById(a1.getId()).getTitle());
		
		APIEvent temp = new APIEvent();
		temp.setTitle("aaaTitle");
		assertEquals(0, apiService.getAll(temp).size());
		temp.setTitle("bbbTitle");
		assertEquals(1, apiService.getAll(temp).size());
		
		apiService.hibernateDelete(a1.getId());
		apiService.hibernateDelete(a2.getId());
		
		assertEquals(apiSize, apiService.getAll(null).size());
	}
	
}

package no.niths.application.rest;

import static org.junit.Assert.assertNotNull;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.web.client.RestTemplate;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
@TransactionConfiguration(transactionManager = "transactionManager")
public class CommitteeControllerTest {

	

	RestTemplate restTemplate = new RestTemplate();
	
	@Test
	public void testAutowireRestTemplate(){
		assertNotNull(restTemplate);
	}
	
	@Test
	public void testCreate() {
		
		
	}

	@Test
	public void testGetById() {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Accept", "application/xml");
		HttpEntity<String> requestEntity = new HttpEntity<String>(requestHeaders);
		HttpEntity<String> response = restTemplate.exchange("http://localhost:8080/niths/committees/1", HttpMethod.GET, requestEntity, String.class);
		assertNotNull(response);
	}

	@Test
	public void testGetAll() {
//		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
//		fail("Not yet implemented");	
	}

	@Test
	public void testDelete() {
//		fail("Not yet implemented");	
	}

}

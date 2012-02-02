package no.niths.application.rest;

import no.niths.common.LoggerTest;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Course;
import no.niths.domain.Student;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.client.RestTemplate;
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class }, loader = AnnotationConfigContextLoader.class)
public class RestTest {

	private static final Logger logger = LoggerFactory
			.getLogger(RestTest.class);

	// @Autowired
	// private RestTemplate template;
	String en = "http://ec2-46-137-44-111.eu-west-1.compute.amazonaws.com:8080/niths/courses/1.json";
	// String all =
	// "http://ec2-46-137-44-111.eu-west-1.compute.amazonaws.com:8080/niths/courses";
	String all = "http://localhost:8080/niths/courses";

	@Test
	@Ignore
	public void testMethod() {
		// Course c = template.getForObject(en, Course.class,new Object[]{});
		String tradeXml = new RestTemplate()
				.getForObject(
						"http://ec2-46-137-44-111.eu-west-1.compute.amazonaws.com:8080/niths/courses/1.json",
						String.class);

		logger.debug(tradeXml);
	}

	@Test
	public void testSome() {
		// Gets the response in a String
		String responseString = new RestTemplate().getForObject(all,
				String.class);
		logger.debug(responseString);
		// Converts to a json array
		Object obj = JSONValue.parse(responseString);
		JSONArray objArray = (JSONArray) obj;

		// Prints all in the json array
		logger.debug("ALL OBJECTS");
		for (int i = 0; i < objArray.size(); i++) {
			logger.debug("Content : " + objArray.get(i));
		}
		JSONObject oneObj;
		// Gets the first json object in the array
		if (objArray.size() >= 1) {
			if (objArray.size() == 1) {
				oneObj = (JSONObject) objArray.get(0);
			} else {
				oneObj = (JSONObject) objArray.get(1);
			}
			// Prints out all in the object
			logger.debug("ONE OBJECT");
			for (int i = 0; i < oneObj.size(); i++) {
				logger.debug("Content: " + oneObj.get(i));
			}
			
			logger.debug("ONE OBJECT: " + oneObj);
		}

	}
}

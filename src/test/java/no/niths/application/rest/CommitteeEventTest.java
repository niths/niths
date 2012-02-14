package no.niths.application.rest;

import no.niths.common.config.TestAppConfig;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class },  loader = AnnotationConfigContextLoader.class)
public class CommitteeEventTest extends BaseWebApplicationContextTests {

	@Test
	public void test() {
		try {

			MockHttpServletRequest request = new MockHttpServletRequest("GET",
					"event/1");

			MockHttpServletResponse response = new MockHttpServletResponse();

			servlet.init(new MockServletConfig());

			servlet.service(request, response);
			String result = response.getContentAsString();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	
	}

}

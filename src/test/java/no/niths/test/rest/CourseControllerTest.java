package no.niths.test.rest;

//import static org.junit.Assert.assertEquals;
//
//import java.io.UnsupportedEncodingException;
//
//import no.niths.domain.Course;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.mock.web.MockHttpServletResponse;
//
//public class CourseControllerTest {
//
//    private MockHttpServletRequest request;
//    private MockHttpServletResponse response;
//
//    @Before
//    public void setUp() {
//        request = new MockHttpServletRequest();
//        response = new MockHttpServletResponse();
//    }
//
//    @Test(expected = UnsupportedEncodingException.class)
//    public void testGetCourse() throws UnsupportedEncodingException {
//
//        // TODO
//        // Create a proper test.
//        final Course course = new Course("foo", "bar");
//
//        request.setRequestURI("/courses/1");
//
//        assertEquals("foo", response.getContentAsString());
//    }
//}

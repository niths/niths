package no.niths.application.rest;

import static org.junit.Assert.assertEquals;
import no.niths.application.rest.interfaces.CourseController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Course;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class CourseControllerTest {

    @Autowired
    private CourseController controller;

    private MockHttpServletResponse res;

    @Before
    public void setUp() {
        res = new MockHttpServletResponse();
    }

    @Test
    public void testCreateAndGetCourse() {
        Course firstCourse = new Course("aCourse", "desc");
        controller.create(firstCourse, res);

        Course secondCourse = controller.getAll(firstCourse).get(0);

        assertEquals(firstCourse.getName(), secondCourse.getName());
        
        controller.hibernateDelete(firstCourse.getId());
        
    }

    @Test
    public void testDeleteCourse() {

        // Persist two courses to ensure the database is not empty
        Course foo = new Course("grault", "garply");
        Course bar = new Course("baz", "qux");
        controller.create(foo, res);
        controller.create(bar, res);

        final int originalCount = controller.getAll(null).size();

        // Persist a course
        Course firstCourse = getRandomCourse();
        controller.create(firstCourse, res);

        // Delete the same course
        Course secondCourse = controller.getAll(firstCourse).get(0);
        controller.hibernateDelete(secondCourse.getId());

        assertEquals(originalCount, controller.getAll(null).size());
    }

    @Test
    public void testUpdateCourse() {
        Course firstCourse = new Course("foo", "bar");
        controller.create(firstCourse, res);

        firstCourse.setName("corge");
        controller.update(firstCourse);

        assertEquals(firstCourse.getName(),
                controller.getAll(firstCourse).get(0).getName());
    }

    // Helper method for creating a Course
    private Course getRandomCourse() {
        return new Course("foo", "bar");
    }
}
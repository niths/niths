package no.niths.application.rest;

import static org.junit.Assert.assertEquals;
import no.niths.application.rest.interfaces.CourseController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Course;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class CourseControllerTest {

    @Autowired
    private CourseController controller;

    @Test
    public void testCreateAndGetCourse() {
        Course firstCourse = getRandomCourse();
        controller.create(firstCourse);

        Course secondCourse = controller.getAll(firstCourse).get(0);

        assertEquals(firstCourse.getName(), secondCourse.getName());
    }

    @Test
    public void testDeleteCourse() {

        // Persist two courses to ensure the database is not empty
        Course foo = new Course("grault", "garply");
        Course bar = new Course("baz", "qux");
        controller.create(foo);
        controller.create(bar);

        final int originalCount = controller.getAll(null).size();

        // Persist a course
        Course firstCourse = getRandomCourse();
        controller.create(firstCourse);

        // Delete the same course
        Course secondCourse = controller.getAll(firstCourse).get(0);
        controller.hibernateDelete(secondCourse.getId());

        assertEquals(originalCount, controller.getAll(null).size());
    }

    @Test
    public void testUpdateCourse() {
        Course firstCourse = new Course("foo", "bar");
        controller.create(firstCourse);

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
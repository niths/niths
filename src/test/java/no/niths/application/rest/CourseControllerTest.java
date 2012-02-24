package no.niths.application.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.CourseController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Course;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
@Transactional
public class CourseControllerTest {

    @Autowired
    private CourseController controller;

    @Test
    @Rollback(true)
    public void testGetAndCreateCourse() {
        Course firstCourse = getRandomCourse();
        controller.create(firstCourse);

        Course secondCourse = controller.getAll(firstCourse).get(0);

        assertEquals(firstCourse.getName(), secondCourse.getName());
    }

    @Test
    @Rollback(true)
    public void testDeleteCourse() {
        final int originalCount = controller.getAll(null).size();

        // Persist a course
        Course firstCourse = getRandomCourse();
        controller.create(firstCourse);

        assertEquals(originalCount + 1, controller.getAll(null).size());

        // Delete the same course
        Course secondCourse = controller.getAll(firstCourse).get(0);
        controller.delete(secondCourse.getId());

        assertEquals(originalCount, controller.getAll(null).size());
    }

    @Test
    @Rollback(true)
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
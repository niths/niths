package no.niths.infrastructure;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Course;
import no.niths.infrastructure.interfaces.CoursesRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= { TestAppConfig.class, HibernateConfig.class})
@Transactional
public class CourseRepositoryTest {
	
	private Course course = new Course("Programmering","Programmeringsfaget");
	
	@Autowired
	private CoursesRepository repo;
	
	
	@Test(expected = IllegalArgumentException.class)
	@Rollback(true)
	public void whenInsertNull_persistenceShouldFail() {
		repo.createCourse(null);
	}
	
	@Test
	@Rollback(true)
	public void whenInsertCourse_CourseShouldBePersisted(){
		int size = repo.getAllCourses().size();
		
		Course c = new Course("Name", "Desc");
		repo.createCourse(c);
		
		assertEquals(size + 1, repo.getAllCourses().size());
	}
	
	@Test
	@Rollback(true)
	public void testCRUD() {
		// creates a course
		course.setId(repo.createCourse(course));
	
		// Get by id
		assertEquals(course , repo.getCourseById(course.getId()));	

		//Updates parameters and entity object
		course.setName("Mobil-Programmering");
		course.setDescription("Mobil prog");
		repo.createCourse(course);
		
		assertEquals(course, repo.getCourseById(course.getId()));
		
		//Delete
		repo.deleteCourse(course.getId());

		assertNull("Should be deleted now",repo.getCourseById(course.getId()));
	}
	
	
	@Test
	@Rollback(true)
	public void testRead(){
		
		Course courseProg = new Course("Programmering", "programmering er kult");

		// adding course
		courseProg.setId(repo.createCourse(courseProg));
	
		assertNotSame(0, repo.getAllCourses().size());
		
		// deleting courses
		repo.deleteCourse(courseProg.getId());
	}
}

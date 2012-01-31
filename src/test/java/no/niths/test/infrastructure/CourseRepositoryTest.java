package no.niths.test.infrastructure;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import no.niths.common.config.AppConfig;
import no.niths.domain.Course;
import no.niths.infrastructure.interfaces.CoursesRepository;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {AppConfig.class})
public class CourseRepositoryTest {
	
	private Course course = new Course("Programmering","Programmeringsfaget");
	
	@Autowired
	private CoursesRepository repo;
	
	@Test
	@Rollback(true)
	public void testCRUD() {
		// creates a course
		course.setId(repo.create(course));
	
		// Get by id
		assertEquals(course , repo.getByCourseId(course.getId()));	

		//Updates parameters and entity object
		course.setName("Mobil-Programmering");
		course.setDescription("Mobil prog");
		repo.update(course);
		
		assertEquals(course, repo.getByCourseId(course.getId()));
		
		//Delete
		repo.delete(course);

		assertNull("Should be deleted now",repo.getByCourseId(course.getId()));
	}
	
	
	@Test
	@Rollback(true)
	public void testRead(){
		
		Course courseProg = new Course("Programmering", "programmering er kult");

		// adding course
		courseProg.setId(repo.create(courseProg));
	
		assertNotSame(0, repo.getAll().size());
		
		// deleting courses
		repo.delete(courseProg);
	}
}

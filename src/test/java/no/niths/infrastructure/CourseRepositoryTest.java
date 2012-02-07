package no.niths.infrastructure;


import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Course;
import no.niths.infrastructure.interfaces.CoursesRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= { TestAppConfig.class, HibernateConfig.class})
@Transactional
public class CourseRepositoryTest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(CourseRepositoryTest.class);
	
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
	public void getCoursesByName_shouldReturnListWithCourses(){
		int  size = repo.getAllCourses().size();
		Course c1 = new Course("One", "oneDesc");
		Course c2 = new Course("Two", "oneDesc");// equal desc
		Course c3 = new Course("Three", "threeDesc");
		Course c4 = new Course("Four", "fourDesc");
		repo.createCourse(c1);
		repo.createCourse(c2);
		repo.createCourse(c3);
		repo.createCourse(c4);
		assertEquals(size + 4, repo.getAllCourses().size());
		
		assertEquals(c1, repo.getCourseByName("One"));
		assertEquals(c2, repo.getCourseByName("Two"));
		assertEquals(null, repo.getCourseByName("XXX"));
	}
	
	@Test
	@Rollback(true)
	public void getCoursesByAttributes_shouldReturnListWithCourses(){
		int  size = repo.getAllCourses().size();
		Course c1 = new Course("One", "oneDesc");
		Course c2 = new Course("Two", "oneDesc");// equal desc
		Course c3 = new Course("Three", "threeDesc");
		Course c4 = new Course("Four", "fourDesc");
		repo.createCourse(c1);
		repo.createCourse(c2);
		repo.createCourse(c3);
		repo.createCourse(c4);
		
		assertEquals(size + 4, repo.getAllCourses().size());
		
		Course toFind = new Course();
		toFind.setName("One");
		
		List<Course> results = repo.getAllCourses(toFind);
		assertEquals(1, results.size());
		
		toFind = new Course();
		toFind.setDescription("oneDesc");
		results = repo.getAllCourses(toFind);
		assertEquals(2, results.size());

		toFind = new Course();
		toFind.setName("x");
		results = repo.getAllCourses(toFind);
		assertEquals(0, results.size());
	}
	
	@Test
	@Rollback(true)
	public void testCRUD() {
		int size = repo.getAllCourses().size();
		Course c = new Course("Name222", "Desc");
		repo.createCourse(c);
		assertEquals(size + 1, repo.getAllCourses().size());
		
		Course c1 = repo.getCourseById(c.getId());
		assertEquals(c1.getId(), c.getId());
		long id = c1.getId();
		
		assertEquals(c1, repo.getCourseByName("Name222"));
		
		boolean isDeleted = repo.deleteCourse(id);
		assertTrue(isDeleted);
		assertEquals(size, repo.getAllCourses().size());
		
		assertNull(repo.getCourseByName("Name222"));
		
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

package no.niths.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Course;
import no.niths.domain.Student;
import no.niths.services.interfaces.CourseService;
import no.niths.services.interfaces.StudentService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
//@Transactional
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class StudentServiceTest {

	private static final Logger logger = LoggerFactory
			.getLogger(StudentServiceTest.class);

	@Autowired
	private StudentService studService;
	
	@Autowired
	private CourseService courseService;

	@Test
	@Rollback(true)
	public void testCRUD() {

		ArrayList<Student> students = (ArrayList<Student>) studService.getAll(null);
		for (int i = 0; i < students.size(); i++) {
			studService.hibernateDelete(students.get(i).getId());
		}

		
		
		assertEquals(true, studService.getAll(null).isEmpty());

		// Testing create
		Student s = new Student("John", "Doe");
		Student x = new Student("Vera", "Fine");
		s.setEmail("mail@mil.com");
		x.setEmail("mail2@mal.com");

		studService.create(s);
		studService.create(x);

		// Testing get
		assertEquals(2, studService.getAll(null).size());
		assertEquals(s, studService.getById(s.getId()));

		// Testing update

		assertEquals("mail@mil.com", studService.getById(s.getId()).getEmail());

		s.setEmail("john@doe.com");
		studService.update(s);
		assertEquals("john@doe.com", studService.getById(s.getId()).getEmail());

		// Testing delete //Don't work in same transaction
		studService.hibernateDelete(s.getId());

		//assertTrue(isDeleted);
		assertEquals(1, studService.getAll(null).size());
		
		studService.hibernateDelete(x.getId());
		assertEquals(true, studService.getAll(null).isEmpty());
	}
	
	@Test
	public void testStudentCoursesRelationship(){
		Course c = new Course("Navn", "Desc");
		courseService.create(c);
		Course c2 = new Course("Navaa", "Descen");
		courseService.create(c2);
		
		Student s = new Student("xxxx@nith.no");
		s.getCourses().add(c);
		s.getCourses().add(c2);
		studService.create(s);
		assertEquals(2, studService.getById(s.getId()).getCourses().size());
		
		//Delete one course
		courseService.hibernateDelete(c.getId());
		//Course should be deleted on student
		assertEquals(1, studService.getById(s.getId()).getCourses().size());
		//Delete the student
		studService.hibernateDelete(s.getId());
		//Course should still be there
		assertEquals(c2, courseService.getById(c2.getId()));
		
	}

}

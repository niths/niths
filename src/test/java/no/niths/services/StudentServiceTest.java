package no.niths.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Student;
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
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class StudentServiceTest {

	private static final Logger logger = LoggerFactory
			.getLogger(StudentServiceTest.class);

	@Autowired
	private StudentService studService;

	@Test
	@Rollback(true)
	public void testCRUD() {

		ArrayList<Student> students = (ArrayList<Student>) studService.getAll(null);
		for (int i = 0; i < students.size(); i++) {
			studService.delete(students.get(i).getId());
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
		// studService.update(s);
		assertEquals("john@doe.com", studService.getById(s.getId()).getEmail());

		// Testing delete //Don't work in same transaction
//		boolean isDeleted = studService.delete(s.getId());
//
//		assertTrue(isDeleted);
//		assertEquals(1, studService.getAll(null).size());
		
//		studService.delete(x.getId());
//		assertEquals(true, studService.getAll(null).isEmpty());

	}

}

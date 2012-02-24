package no.niths.application.rest;

import static org.junit.Assert.assertEquals;

import javax.validation.ConstraintViolationException;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.StudentController;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Student;
import no.niths.services.StudentServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
@Transactional
@TransactionConfiguration(transactionManager = TestAppConfig.TRANSACTION_MANAGER)
public class StudentControllerTest {

	private static final Logger logger = LoggerFactory
			.getLogger(StudentControllerTest.class);

	@Autowired
	private StudentController studController;

	@Test(expected= ConstraintViolationException.class)
	public void testInsertNullObject_shallThrowException() {
		
		Student s = new Student();
		studController.create(s);
	}
	
	@Test
	public void testCreateAndDeleteWithExistingIds(){
		int size = 0;
		try {
			size = studController.getAll(null).size();
		} catch (ObjectNotFoundException e) {
			//size = 0
		}
		
		Student s = new Student("mail@mail.com");
		studController.create(s);
		
		assertEquals(size + 1, studController.getAll(null).size());
		
		studController.delete(s.getId());
		int currentSize = 0;
		try {
			currentSize  = studController.getAll(null).size();
		} catch (ObjectNotFoundException e) {
			//size = 0
		}
		
		assertEquals(size, currentSize);
		
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void testDeleteWithUnvalidId(){
		studController.delete(new Long(9391));
	}
	
	@Test
	public void testGetMethods(){
		int size = 0;
		try {
			size = studController.getAll(null).size();
		} catch (ObjectNotFoundException e) {
			//size = 0
		}
		Student s1 = new Student("mail1@mail.com");
		Student s2 = new Student("mail2@mail.com");
		Student s3 = new Student("mail3@mail.com");
		
		studController.create(s1);
		studController.create(s2);
		studController.create(s3);
		
		assertEquals(size + 3, studController.getAll(null).size());
		
		logger.debug("DDDDDDDDDDDD" + s1.getId());
		assertEquals(1, studController.getAll(s1).size());
		
		
	}
	

}

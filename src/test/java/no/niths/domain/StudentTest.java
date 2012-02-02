package no.niths.domain;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import no.niths.domain.Student;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StudentTest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(StudentTest.class); 

	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testStudentValues() {

		Student student = new Student("John", "Doe", 'M', 1, "john@doe.com",
				"12345678", "Student at NITH");

		// Should pass validation
		Set<ConstraintViolation<Student>> constraintViolations = validator
				.validate(student);
		
		assertEquals(0, constraintViolations.size());

		// Should not pass validation
		student.setEmail("John.gmail.com");
		constraintViolations = validator.validate(student);
		assertEquals(1, constraintViolations.size());
		//Prints the error message
		logger.debug(constraintViolations.iterator().next().getMessage());
		
		// Should not pass validation
		student.setEmail("John.gmail.com");
		student.setTelephoneNumber("123");
		constraintViolations = validator.validate(student);
		assertEquals(2, constraintViolations.size());
		
		Student student2 = new Student("John", "Doe", 'K', 5, "john@doe.com",
				"12345678", "Student at NITH");
		constraintViolations = validator.validate(student2);
		assertEquals(2, constraintViolations.size());
		
		student2.setSex('M');
		constraintViolations = validator.validate(student2);
		assertEquals(1, constraintViolations.size());
		
		student2.setGrade(1);
		constraintViolations = validator.validate(student2);
		assertEquals(0, constraintViolations.size());
		
		
		
	}
}

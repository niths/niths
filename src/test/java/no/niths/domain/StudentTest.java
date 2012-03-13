package no.niths.domain;

import java.util.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StudentTest {
        private static final Long ID = 1L;
        private static final String FIRST_NAME = "John";
        private static final String LAST_NAME = "Doe";
        private static final char GENDER = 'M';
        private static final Integer GRADE = 1;
        private static final String EMAIL = "john@doe.com";
        private static final String PHONE_NUMBER = "12345678";
        private static final String DESCRIPTION = "Student at NITH";
        private static final String PASSWORD = "Hello";
        private static final Date DATE = new Date();
	
	private static final Logger logger = LoggerFactory
			.getLogger(StudentTest.class);

	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
        
        @Test
        public void testShouldGenerateNewStudent() {
                Student student = new Student(FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER);
                student.setGender(GENDER);
                student.setGrade(GRADE);
                student.setDescription(DESCRIPTION);
                student.setPassword(PASSWORD);
                student.setBirthday(DATE);

                assertThat(FIRST_NAME, is(equalTo(student.getFirstName())));
                assertThat(LAST_NAME, is(equalTo(student.getLastName())));
                assertThat(GENDER, is(equalTo(student.getGender())));
                assertThat(GRADE, is(equalTo(student.getGrade())));
                assertThat(EMAIL, is(equalTo(student.getEmail())));
                assertThat(PHONE_NUMBER, is(equalTo(student.getTelephoneNumber())));
                assertThat(DESCRIPTION, is(equalTo(student.getDescription())));
                assertThat(PASSWORD, is(equalTo(student.getPassword())));
                assertThat(DATE, is(equalTo(student.getBirthday())));
                assertThat(student.getSerialversionuid(), is(notNullValue()));
                
                assertThat(false, is(equalTo(student.isEmpty())));
        }
        
        @Test
        public void testTwoEqualStudents() {
                Student student = new Student(ID, FIRST_NAME, LAST_NAME);
                student.setGender(GENDER);
                student.setGrade(GRADE);
                student.setEmail(EMAIL);
                student.setTelephoneNumber(PHONE_NUMBER);
                student.setDescription(DESCRIPTION);
                
                Student equalStudent = student;
                
                assertThat(true, is(equalTo(student.equals(equalStudent))));
        }
        
        @Test
        public void testTwoStudentsWhichIsNotEqual() {
                Student student = new Student(ID, FIRST_NAME, LAST_NAME);
                student.setGender(GENDER);
                student.setGrade(GRADE);
                student.setEmail(EMAIL);
                student.setTelephoneNumber(PHONE_NUMBER);
                student.setDescription(DESCRIPTION);
                
                Student notEqualStudent = new Student(FIRST_NAME, LAST_NAME, GENDER, GRADE, EMAIL,
				PHONE_NUMBER, DESCRIPTION);
                
                assertThat(false, is(equalTo(student.equals(notEqualStudent))));
        }
        
        @Test
        public void testEqualsBetweenNotEqualObjects() {
                Student student = new Student(ID, FIRST_NAME, LAST_NAME);
                student.setGender(GENDER);
                student.setGrade(GRADE);
                student.setEmail(EMAIL);
                student.setTelephoneNumber(PHONE_NUMBER);
                student.setDescription(DESCRIPTION);
                
                Course course = new Course();
                
                assertThat(false, is(equalTo(student.equals(course))));
        }
        
        @Test
        public void testEmptyStudentObject() {
                Student student = new Student();
                
                assertThat(true, is(equalTo(student.isEmpty())));
        }

	@Test
	public void testValidationOfCorectStudentValues() {
		Student student = new Student(FIRST_NAME, LAST_NAME, GENDER, GRADE, EMAIL,
				PHONE_NUMBER, DESCRIPTION);

		Set<ConstraintViolation<Student>> constraintViolations = validator
				.validate(student);
                
                assertThat(0, is(equalTo(constraintViolations.size())));		
	}
        
        @Test
	public void testValidationOfIncorectStudentValues() {
		Student student = new Student(FIRST_NAME, LAST_NAME, 'K', 5, EMAIL,
				PHONE_NUMBER, DESCRIPTION);

		Set<ConstraintViolation<Student>> constraintViolations = validator
				.validate(student);

		constraintViolations = validator.validate(student);
		assertThat(2, is(equalTo(constraintViolations.size())));		
	}
}

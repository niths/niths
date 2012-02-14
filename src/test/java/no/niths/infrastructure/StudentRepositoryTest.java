package no.niths.infrastructure;
import static org.junit.Assert.assertEquals;

import java.util.Date;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.Committee;
import no.niths.domain.Course;
import no.niths.domain.Student;
import no.niths.infrastructure.interfaces.CommitteesRepository;
import no.niths.infrastructure.interfaces.CoursesRepository;
import no.niths.infrastructure.interfaces.StudentRepository;

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
public class StudentRepositoryTest {
	
	private static final Logger logger = LoggerFactory
	.getLogger(StudentRepositoryTest.class); // Replace with test class

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private CoursesRepository courseRepo;
	
	@Autowired
	private CommitteesRepository comRepo;

	/**
	 * Testing of basic CRUD functions
	 */
	@Test
	@Rollback(true)
	public void testCRUD(){
		Student stud = new Student("John", "Doe");
		studentRepo.create(stud);
		
		assertEquals(stud, studentRepo.getById(stud.getId()));
		assertEquals(1, studentRepo.getAllStudents().size());
		
		stud.setFirstName("Jane");
		studentRepo.update(stud);
		
		assertEquals("Jane", studentRepo.getById(stud.getId()).getFirstName());
	
		studentRepo.delete(stud);
		assertEquals(null, studentRepo.getById(stud.getId()));
		
		assertEquals(true, studentRepo.getAllStudents().isEmpty());

	}
	
	/**
	 * Tests the course association
	 */
	@Test
	@Rollback(true)
	public void testStudentWithCourses(){
		Course c1 = new Course("PROG", "casd1");
		Course c2 = new Course("DESIGN", "cdda2");
		
		courseRepo.createCourse(c1);
		courseRepo.createCourse(c2);
		//Courses should be persisted
		assertEquals(2, courseRepo.getAllCourses().size());
		
		
		Student stud = new Student("John", "Doe");
		stud.getCourses().add(c1);
		stud.getCourses().add(c2);

		studentRepo.create(stud);
		assertEquals(stud, studentRepo.getById(stud.getId()));
		//Test if student has courses
		assertEquals(2, studentRepo.getById(stud.getId()).getCourses().size());
		
		studentRepo.getById(stud.getId()).getCourses().remove(1);
		assertEquals(1, studentRepo.getById(stud.getId()).getCourses().size());
		
		assertEquals(2, courseRepo.getAllCourses().size());
		
		Committee c = new Committee("Utvalg", "desc");
		
		comRepo.create(c);
		assertEquals(1, comRepo.getAllCommittees().size());
		stud = studentRepo.getById(stud.getId());
		stud.getCommittees().add(c);
		
		studentRepo.update(stud);
		
		assertEquals(1, studentRepo.getById(stud.getId()).getCommittees().size());
		
	}
	
	@Test
	@Rollback(true)
	public void getAllStudentsWithParameter_shouldReturnListOfStudentsMatching(){
		int size = studentRepo.getAllStudents().size();
		Student s1 = new Student("John", "Doe");
		Student s2 = new Student("John", "Doe");
		Student s3 = new Student("Jane", "Doe");
		Student s4 = new Student("Foo", "Bar");
		studentRepo.create(s1);
		studentRepo.create(s2);
		studentRepo.create(s3);
		studentRepo.create(s4);
		assertEquals(size  + 4, studentRepo.getAllStudents().size());
		
		Student toFind = new Student("John", "Doe");		
		assertEquals(2, studentRepo.getAllStudents(toFind).size());
		
		toFind = new Student("Jane", "Doe");
		assertEquals(1, studentRepo.getAllStudents(toFind).size());
		
		toFind = new Student("XXX", "Doe");
		assertEquals(0, studentRepo.getAllStudents(toFind).size());
		
		
	}
	
	

	@Test
	@Rollback(true)
	public void testGetByName() {
		//Adding 3 students
		Student s1 = new Student("John", "Doe");
		Student s2 = new Student("Mando", "Doe");
		Student s3 = new Student("Jane", "Doe");
		
		studentRepo.create(s1);
		studentRepo.create(s2);
		studentRepo.create(s3);
		
		
		
//		assertNotSame(0, studentRepo.getAllStudents().size());		
//		assertNotSame(0, studentRepo.getByName("").size());		
//		assertNotSame(0, studentRepo.getByName("John").size());		
//		assertNotSame(0, studentRepo.getByName("Jo").size());		
//		assertNotSame(0, studentRepo.getByName("hn").size());		
//		assertEquals(true, studentRepo.getByName("non").isEmpty());
		
	}
	
//	@Test
//	@Rollback(true)
//	public void shouldReturnAllStudentsWithThatName(){
//		int size = studentRepo.getAllStudents().size();
//		
//		Student s1 = new Student("John", "Doe");		
//		Student s2 = new Student("Vera", "Fine");		
//		Student s3 = new Student("Vera", "Fine");		
//		Student s4 = new Student("Vera", "Fine");		
//		Student s5 = new Student("Vera", "Fine");		
//		studentRepo.create(s1);
//		studentRepo.create(s2);
//		studentRepo.create(s3);
//		studentRepo.create(s4);
//		studentRepo.create(s5);
//		
//		assertEquals(size + 5, studentRepo.getAllStudents().size());
//		
//		String term = "Vera Fine";
//		
//		assertEquals(4, studentRepo.get(term).size());
//		
//		
//	}



}

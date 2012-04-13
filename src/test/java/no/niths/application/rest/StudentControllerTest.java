package no.niths.application.rest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import javax.validation.ConstraintViolationException;

import no.niths.application.rest.exception.ObjectNotFoundException;
import no.niths.application.rest.interfaces.*;
import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.*;
import no.niths.domain.battlestation.Loan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.GregorianCalendar;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class StudentControllerTest {

    public static final String EMAIL = "epost@nith.no";

    @Autowired
	private StudentController studController;

    @Autowired
	private CourseController courseController;

    @Autowired
	private CommitteeController committeeController;

    @Autowired
	private FeedController feedController;

    @Autowired
	private LoanController loanController;

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
		
		studController.hibernateDelete(s.getId());
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
		studController.hibernateDelete(new Long(9391));
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
		
		assertEquals(s1, studController.getById(s1.getId()));
		
		assertEquals(1, studController.getAll(s1).size());
		
		s1.setEmail("xxx@mail.com");
		assertEquals("mail1@mail.com", studController.getById(s1.getId()).getEmail());
		
		studController.update(s1);
		assertEquals("xxx@mail.com", studController.getById(s1.getId()).getEmail());
	}

    @Test
    public void testCreateAndDeleteOfCourses() {
        Student student = new Student(EMAIL);
        studController.create(student);

        assertThat(student, is(equalTo(studController.getById(student.getId()))));

        Course course = new Course("Programmering", "Litt av hvert");
        Course otherCourse = new Course("Spillprogrammering", "Mye rart");

        courseController.create(course);
        courseController.create(otherCourse);

        studController.addCourse(student.getId(), course.getId());
        studController.addCourse(student.getId(), otherCourse.getId());

        assertThat(2, is(equalTo(studController.getById(student.getId()).getCourses().size())));

        studController.removeCourse(student.getId(), course.getId());

        assertThat(1, is(equalTo(studController.getById(student.getId()).getCourses().size())));
        assertThat(courseController.getById(otherCourse.getId()).getId(), is(equalTo(studController.getById(student.getId()).getCourses().get(0).getId())));

        studController.hibernateDelete(student.getId());
        courseController.hibernateDelete(course.getId());
        courseController.hibernateDelete(otherCourse.getId());
    }

    @Test
    public void testCreateAndDeleteOfCommittees() {
        Student student = new Student(EMAIL);
        studController.create(student);

        assertThat(student, is(equalTo(studController.getById(student.getId()))));

        Committee committee = new Committee("UFF", "Utvalg for Fantastiske fritidssysseler");
        Committee otherCommittee = new Committee("KIT", "Kvinner og IT");

        committeeController.create(committee);
        committeeController.create(otherCommittee);

        studController.addCommittee(student.getId(), committee.getId());
        studController.addCommittee(student.getId(), otherCommittee.getId());

        assertThat(2, is(equalTo(studController.getById(student.getId()).getCommittees().size())));

        studController.removeCommittee(student.getId(), committee.getId());

        assertThat(1, is(equalTo(studController.getById(student.getId()).getCommittees().size())));
        assertThat(committeeController.getById(otherCommittee.getId()).getId(), is(equalTo(studController.getById(student.getId()).getCommittees().get(0).getId())));

        studController.hibernateDelete(student.getId());
        committeeController.hibernateDelete(committee.getId());
        committeeController.hibernateDelete(otherCommittee.getId());
    }

    @Test
    public void testCreateAndDeleteOfFeeds() {
        Student student = new Student(EMAIL);
        studController.create(student);

        assertThat(student, is(equalTo(studController.getById(student.getId()))));

        Feed feed = new Feed("Husk at kroa er åpen");
        Feed otherFeed = new Feed("Minglekveld");

        feedController.create(feed);
        feedController.create(otherFeed);

        studController.addFeed(student.getId(), feed.getId());
        studController.addFeed(student.getId(), otherFeed.getId());

        assertThat(2, is(equalTo(studController.getById(student.getId()).getFeeds().size())));

        studController.removeFeed(student.getId(), feed.getId());

        assertThat(1, is(equalTo(studController.getById(student.getId()).getFeeds().size())));
        assertThat(feedController.getById(otherFeed.getId()).getId(), is(equalTo(studController.getById(student.getId()).getFeeds().get(0).getId())));

        studController.hibernateDelete(student.getId());
        feedController.hibernateDelete(feed.getId());
        feedController.hibernateDelete(otherFeed.getId());
    }

    @Test
    public void testCreateAndDeleteOfConsoles() {
        Student student = new Student(EMAIL);
        studController.create(student);

        assertThat(student, is(equalTo(studController.getById(student.getId()))));

        Loan loan = new Loan(new GregorianCalendar(), new GregorianCalendar());
        Loan otherLoan = new Loan(new GregorianCalendar());

        loanController.create(loan);
        loanController.create(otherLoan);

        studController.addLoan(student.getId(), loan.getId());
        studController.addLoan(student.getId(), otherLoan.getId());

        assertThat(2, is(equalTo(studController.getById(student.getId()).getLoans().size())));

        studController.removeLoan(student.getId(), loan.getId());

        assertThat(1, is(equalTo(studController.getById(student.getId()).getLoans().size())));
        assertThat(loanController.getById(otherLoan.getId()).getId(), is(equalTo(studController.getById(student.getId()).getLoans().get(0).getId())));

        studController.hibernateDelete(student.getId());
        loanController.hibernateDelete(loan.getId());
        loanController.hibernateDelete(otherLoan.getId());
    }
    
    @Test
    public void testGetStudentWithRoles(){
    	//TODO make test here!
    }
}

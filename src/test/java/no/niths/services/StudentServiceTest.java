package no.niths.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;
import no.niths.domain.battlestation.Loan;
import no.niths.domain.school.Committee;
import no.niths.domain.school.Course;
import no.niths.domain.school.Feed;
import no.niths.domain.school.Student;
import no.niths.domain.security.Role;
import no.niths.services.battlestation.interfaces.LoanService;
import no.niths.services.interfaces.RoleService;
import no.niths.services.school.interfaces.CommitteeService;
import no.niths.services.school.interfaces.CourseService;
import no.niths.services.school.interfaces.FeedService;
import no.niths.services.school.interfaces.StudentService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class StudentServiceTest {

    public static final String EMAIL = "enEmail@nith.no";

    @Autowired
	private StudentService studService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private CommitteeService comService;

    @Autowired
	private FeedService feedService;

    @Autowired
	private LoanService loanService;

	@Test
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
	public void testStudentRoleRelationship(){
		Student s = new Student("xxxxx@nith.no");
		studService.create(s);
		Student fetched = studService.getStudentWithRoles(s.getId());
		assertEquals(s, fetched);
		int studentRoles = fetched.getRoles().size();
		int numOfRoles = roleService.getAll(null).size();
		
		Role r1 = new Role("ROLE_XXX");
		Role r2 = new Role("ROLE_XXX2");
		roleService.create(r1);
		roleService.create(r2);
		assertEquals(numOfRoles + 2,  roleService.getAll(null).size());
		
		fetched.getRoles().add(r1);
		fetched.getRoles().add(r2);
		studService.update(fetched);
		fetched = studService.getStudentWithRoles(s.getId());
		assertEquals(studentRoles + 2, fetched.getRoles().size());
		
		//Delete a ROLE
		roleService.hibernateDelete(r1.getId());
		//Should be removed from student!
		fetched = studService.getStudentWithRoles(s.getId());
		assertEquals(studentRoles + 1, fetched.getRoles().size());
		
		//Delete the student
		studService.hibernateDelete(s.getId());
		//Role should still exist!
		Role r2Copy = roleService.getById(r2.getId());
		assertEquals(r2, r2Copy);
		
		//Cleanup
		roleService.hibernateDelete(r2.getId());
		
		assertEquals(numOfRoles,  roleService.getAll(null).size());
		
		Student aStud1 = new Student("testing@test.com");
		Student aStud2 = new Student("testing2@test.com");
		studService.create(aStud1);
		studService.create(aStud2);
		
		Role aRole = new Role("ROLE_WWWW");
		roleService.create(aRole);
		
		aStud1.getRoles().add(aRole);
		studService.update(aStud1);
		aStud2.getRoles().add(aRole);
		studService.update(aStud2);
		
		roleService.hibernateDelete(aRole.getId());
		studService.hibernateDelete(aStud1.getId());
		studService.hibernateDelete(aStud2.getId());
		
	}
	
	@Test
	public void testRoleCascadeOperations() {
		Student s1= new Student("swish@mailed.com");
		Student s2= new Student("swosh@mailed.com");
		studService.create(s1);
		studService.create(s2);
		
		Role r1 = new Role("SWISH");
		Role r2 = new Role("SWOSH");
		roleService.create(r1);
		roleService.create(r2);
		
		Student temp = studService.getStudentWithRoles(s1.getId());
		Student temp2 = studService.getStudentWithRoles(s2.getId());
		int st = temp.getRoles().size();
		int st2 = temp.getRoles().size();
		temp.getRoles().add(r1);
		temp.getRoles().add(r2);
		temp2.getRoles().add(r1);
		temp2.getRoles().add(r2);
		
		studService.update(temp);
		studService.update(temp2);
		
		temp = studService.getStudentWithRoles(s1.getId());
		temp2 = studService.getStudentWithRoles(s2.getId());
		assertEquals(st + 2, temp.getRoles().size());
		assertEquals(st2 + 2, temp2.getRoles().size());
		
		roleService.hibernateDelete(r1.getId());
		
		temp = studService.getStudentWithRoles(s1.getId());
		temp2 = studService.getStudentWithRoles(s2.getId());
		assertEquals(st + 1, temp.getRoles().size());
		assertEquals(st2 + 1, temp2.getRoles().size());
		
		temp.getRoles().remove(r2);
		studService.update(temp);
		
		temp = studService.getStudentWithRoles(s1.getId());
		temp2 = studService.getStudentWithRoles(s2.getId());
		assertEquals(st, temp.getRoles().size());
		assertEquals(st2 + 1, temp2.getRoles().size());
		
		studService.hibernateDelete(temp.getId());
		temp2 = studService.getStudentWithRoles(s2.getId());
		assertEquals(st2 + 1, temp2.getRoles().size());
		
		studService.hibernateDelete(temp2.getId());
		roleService.hibernateDelete(r2.getId());
	}
	
	@Test(expected=DataIntegrityViolationException.class)
	public void testAddSameRoleToStudent(){
		Student s1= new Student("swish@mailed.com");
		studService.create(s1);
		Role r1 = new Role("SWISH");
		roleService.create(r1);
		
		s1 = studService.getStudentWithRoles(s1.getId());
		int size = s1.getRoles().size();
		s1.getRoles().add(r1);
		s1.getRoles().add(r1);
		studService.update(s1);
		
		s1 = studService.getStudentWithRoles(s1.getId());
		assertEquals(size + 2, s1.getRoles().size());
		
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

        courseService.hibernateDelete(c2.getId());

    }
	
	@Test
	public void testStudentCommitteesRelationship(){
		Student s = new Student("xxx@nith.no");
		studService.create(s);
		int comSize = comService.getAll(null).size();
		Committee c1 = new Committee();
		Committee c2 = new Committee();
		comService.create(c1);
		comService.create(c2);
		assertEquals(comSize + 2, comService.getAll(null).size());
		
		Student fetched = studService.getById(s.getId());
		fetched.getCommittees().add(c1);
		fetched.getCommittees().add(c2);
		studService.update(fetched);
		fetched = studService.getById(s.getId());
		assertEquals(2, fetched.getCommittees().size());
		
		//Delete a committee
		comService.hibernateDelete(c1.getId());
		assertEquals(comSize + 1, comService.getAll(null).size());
		//Should be removed from student
		fetched = studService.getById(s.getId());
		assertEquals(1, fetched.getCommittees().size());
		
		//Delete student
		studService.hibernateDelete(s.getId());
		//Course should still exist
		assertEquals(c2, comService.getById(c2.getId()));
		
		comService.hibernateDelete(c2.getId());
		assertEquals(comSize, comService.getAll(null).size());
	}

    @Test
    public void testRelationsBetweenStudentAndCourse(){
        Course course = new Course("Programmering", "Her lærer du mye rart");
        courseService.create(course);

        Course otherCourse = new Course("Spillprogrammering", "Her undervises det i å lage spill");
        courseService.create(otherCourse);

        Student student = new Student(EMAIL);
        studService.create(student);

        student.getCourses().add(course);
        student.getCourses().add(otherCourse);
        studService.update(student);

        assertThat(2, is(equalTo(studService.getById(student.getId()).getCourses().size())));

        studService.hibernateDelete(student.getId());
        courseService.hibernateDelete(course.getId());
        courseService.hibernateDelete(otherCourse.getId());
    }

    @Test
    public void testRelationsBetweenStudentAndCommittee(){
        Committee committee = new Committee("UFF", "Utvalg for fantastiske fritidssysseler");
        comService.create(committee);

        Committee otherCommittee = new Committee("KIT", "Kvinner og IT");
        comService.create(otherCommittee);

        Student student = new Student(EMAIL);
        studService.create(student);

        student.getCommittees().add(committee);
        student.getCommittees().add(otherCommittee);
        studService.update(student);

        assertThat(2, is(equalTo(studService.getById(student.getId()).getCommittees().size())));

        studService.hibernateDelete(student.getId());
        comService.hibernateDelete(committee.getId());
        comService.hibernateDelete(otherCommittee.getId());
    }

    @Test
    public void testRelationsBetweenStudentAndFeed(){
        Feed feed = new Feed("Husk krokveld");
        feedService.create(feed);

        Feed otherFeed = new Feed("Studentkruspåmelding");
        feedService.create(otherFeed);

        Student student = new Student(EMAIL);
        studService.create(student);

        student.getFeeds().add(feed);
        student.getFeeds().add(otherFeed);
        studService.update(student);

        assertThat(2, is(equalTo(studService.getById(student.getId()).getFeeds().size())));

        studService.hibernateDelete(student.getId());
        feedService.hibernateDelete(feed.getId());
        feedService.hibernateDelete(otherFeed.getId());
    }

    @Test
    public void testRelationsBetweenStudentAndLoan(){
        Loan loan = new Loan(new GregorianCalendar(), new GregorianCalendar());
        loanService.create(loan);

        Loan otherLoan = new Loan(new GregorianCalendar());
        loanService.create(otherLoan);

        Student student = new Student(EMAIL);
        studService.create(student);

        student.getLoans().add(loan);
        student.getLoans().add(otherLoan);
        studService.update(student);

        assertThat(2, is(equalTo(studService.getById(student.getId()).getLoans().size())));

        studService.hibernateDelete(student.getId());
        loanService.hibernateDelete(loan.getId());
        loanService.hibernateDelete(otherLoan.getId());
    }
}

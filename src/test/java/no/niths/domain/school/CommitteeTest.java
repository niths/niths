package no.niths.domain.school;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import no.niths.domain.school.Committee;
import no.niths.domain.school.Event;
import no.niths.domain.school.Student;

import org.junit.BeforeClass;
import org.junit.Test;

public class CommitteeTest {
    private static final Long ID = 1L;
    private static final String NAME        = "UFF",
                                DESCRIPTION =
                                    "Utvalg for Fantasiske Fritidssysler";

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @Test
    public void testShouldGenerateNewCommittee() {
        Committee committee = new Committee(NAME, DESCRIPTION);

        assertThat(NAME, is(equalTo(committee.getName())));
        assertThat(DESCRIPTION, is(equalTo(committee.getDescription())));

        assertThat(false, is(equalTo(committee.isEmpty())));
    }

    @Test
    public void testTwoEqualCommittees() {
        Committee committee1 = new Committee(ID, NAME, DESCRIPTION);
        Committee committee2 = committee1;

        assertThat(true, is(equalTo(committee1.equals(committee2))));
    }

    @Test
    public void testTwoCommitteesWhichAreNotEqual() {
        assertThat(false, is(equalTo(
                new Committee(ID, NAME, DESCRIPTION).equals(
                        new Committee(NAME, DESCRIPTION)))));
    }

    @Test
    public void testEqualsBetweenNotEqualObjects() {
        assertThat(false, is(equalTo(
                new Committee(ID, NAME, DESCRIPTION).equals(new Student()))));
    }

    @Test
    public void testEmptyCommitteeObject() {
        assertThat(true, is(equalTo(new Committee().isEmpty())));
    }


    @Test
    public void testValidationOfCorrectCommitteeValues() {
        Set<ConstraintViolation<Committee>> constraintViolations = validator
                        .validate(new Committee(NAME, DESCRIPTION));

        assertThat(0, is(equalTo(constraintViolations.size())));        
    }

    @Test
    public void testValidationOfIncorectCommitteeValues() {
        Committee committee = new Committee("' OR '1' = '1", DESCRIPTION);

        Set<ConstraintViolation<Committee>> constraintViolations = validator
                        .validate(committee);

        constraintViolations = validator.validate(committee);
        assertThat(1, is(equalTo(constraintViolations.size())));        
    }

    @Test
    public void testGettingEventFromCommittee() {
        Event event = new Event();
        
        List<Event> eventList = new ArrayList<Event>();
        eventList.add(event);

        Committee committee = new Committee();
        committee.setEvents(eventList);

        assertThat(event, is(equalTo(committee.getEvents().get(0))));
    }

    @Test
    public void testGettingStudentLeaderFromCommittee() {
        Student leader = new Student();

        List<Student> leaderList = new ArrayList<Student>();
        leaderList.add(leader);

        Committee committee = new Committee();
        committee.setLeaders(leaderList);

        assertThat(leader, is(equalTo(committee.getLeaders().get(0))));
    }
}
package no.niths.domain;

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

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommitteeTest {
    private static final Long ID = 1L;
    private static final String NAME = "UFF";
    private static final String DESCRIPTION = "Utvalg for Fantasiske Fritidssysler";
    
    private static final Logger logger = LoggerFactory
            .getLogger(CommitteeTest.class);

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @Test
    public void testShouldGenerateNewCommittee() {
        Committee committee = new Committee();
        committee.setName(NAME);
        committee.setDescription(DESCRIPTION);

        assertThat(NAME, is(equalTo(committee.getName())));
        assertThat(DESCRIPTION, is(equalTo(committee.getDescription())));
        
        assertThat(false, is(equalTo(committee.isEmpty())));
    }

    @Test
    public void testTwoEqualCommittees() {
        Committee committee = new Committee(ID, NAME, DESCRIPTION);

        Committee equalCommittee = committee;

        assertThat(true, is(equalTo(committee.equals(equalCommittee))));
    }

    @Test
    public void testTwoCommitteesWhichIsNotEqual() {
        Committee committee = new Committee(ID, NAME, DESCRIPTION);

        Committee notEqualCommittee = new Committee(NAME, DESCRIPTION);

        assertThat(false, is(equalTo(committee.equals(notEqualCommittee))));
    }

    @Test
    public void testEqualsBetweenNotEqualObjects() {
        Committee committee = new Committee(ID, NAME, DESCRIPTION);

        Student student = new Student();

        assertThat(false, is(equalTo(committee.equals(student))));
    }
    
    @Test
    public void testEmptyCommitteeObject() {
        Committee committee = new Committee();

        assertThat(true, is(equalTo(committee.isEmpty())));
    }


    @Test
    public void testValidationOfCorectCommitteeValues() {
        Committee committee = new Committee(NAME, DESCRIPTION);

        Set<ConstraintViolation<Committee>> constraintViolations = validator
                        .validate(committee);

        assertThat(0, is(equalTo(constraintViolations.size())));		
    }

    @Test
    public void testValidationOfIncorectStudentValues() {
        Committee committee = new Committee("UF", DESCRIPTION);

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

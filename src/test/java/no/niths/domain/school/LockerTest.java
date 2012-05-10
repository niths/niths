package no.niths.domain.school;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.BeforeClass;
import org.junit.Test;

public class LockerTest {
    private final Long ID = 1L;
    private final String LOCKER_NUMBER = "001";

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void testShouldCreateNewLocker() {
        assertEquals(
                LOCKER_NUMBER,
                new Locker(LOCKER_NUMBER).getLockerNumber());
    }

    @Test
    public void testInvalidLockerNumberIsInvalid() {

        // Too many digits
        String invalidLockerNumber = "123456";

        // The erroneous value should be invalidLockerNumber
        assertEquals(
                invalidLockerNumber,
                validator.validate(new Locker(invalidLockerNumber))
                        .iterator().next().getInvalidValue());
    }

    @Test
    public void testValid(){
        
    }
    
    @SuppressWarnings("serial")
    @Test
    public void testGettingLockersOwner() {
        final Locker locker = new Locker(ID, LOCKER_NUMBER);

        Student student = new Student();
        student.setLockers(new ArrayList<Locker>() {{ add(locker); }});

        assertEquals(student.getLockers().get(0), locker);
    }
}
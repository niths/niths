package no.niths.domain.location;

import static org.junit.Assert.assertEquals;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

public class RoomTest {

    private static Validator validator;
    
    @BeforeClass
    public static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testCreateLocationWithInvalidPlace() {
        final String invalidPlace = "</script>alert();</script>";

        assertEquals(
                invalidPlace,
                validator.validate(new Location(invalidPlace)).iterator()
                        .next().getInvalidValue());
    }
}
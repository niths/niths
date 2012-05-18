package no.niths.domain.location;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.BeforeClass;
import org.junit.Test;

public class LocationTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void testShouldCreateNewLoaction() {
        final Long id      = 1L;
        final Double lat   = 10.3030;
        final Double lng   = 20.12312;
        final String place = "Oslo";

        Location location = new Location(place, lng, lat);
        location.setId(id);

        assertThat(id,    is(equalTo(location.getId())));
        assertThat(lat,   is(equalTo(location.getLatitude())));
        assertThat(lng,   is(equalTo(location.getLongitude())));
        assertThat(place, is(equalTo(location.getPlace())));
    }

    @Test
    public void testValidationOfIncorectLocationPlaceName() {
        final String invalidPlace = "?";

        // Cannot be null
        assertEquals(
                null,
                validator.validate(
                        new Location()).iterator().next().getInvalidValue());

        // Cannot be "?", and the length should also be > 1
        assertEquals(
                invalidPlace,
                validator.validate(new Location(
                        invalidPlace)).iterator().next().getInvalidValue());
    }
}
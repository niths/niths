package no.niths.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

public class LocationTest {

	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
      
	
	@Test
	public void testShouldCreateNewLoaction() {
		long id = 1;
		double lat = 10.3030;
		double lng = 20.12312;
		String place= "Oslo";
		Location location = new Location(place, lng, lat);
		location.setId(id);

		assertThat(id, is(equalTo(location.getId())));
		assertThat(lat, is(equalTo(location.getLatitude())));
		assertThat(lng, is(equalTo(location.getLongitude())));
		assertThat(place, is(equalTo(location.getPlace())));
	}

	@Test
	public void testValidationOfIncorectLocationPlaceName() {
		Location location = new Location();
		
		Set<ConstraintViolation<Location>> constraintViolations = validator
				.validate(location);
		
	    assertThat(1, is(equalTo(constraintViolations.size())));		
	}
}

package no.niths.domain.location;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;

public class RoomTest {

	@SuppressWarnings("unused")
	private static Validator validator;
    
	@BeforeClass
    public void setup() {
    	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    	validator = factory.getValidator();
    }

   //TODO create Room validation test
}
package no.niths.domain;

import no.niths.domain.Committee;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CommitteeTest {

    private static final Logger logger = LoggerFactory
            .getLogger(StudentTest.class);

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testCommitteeValues() {

        Committee committee = new Committee("UFF", "Utvalg for Fantasiske Fritidssysler");

        // Should pass validation
        Set<ConstraintViolation<Committee>> constraintViolations = validator
                .validate(committee);

        assertThat(0, is(equalTo(constraintViolations.size())));

        // Should not pass validation
        committee.setName("UF");
        constraintViolations = validator.validate(committee);
        assertThat(1, is(equalTo(constraintViolations.size())));

        //Prints the error message
        logger.debug(constraintViolations.iterator().next().getMessage());

        Committee committee2 = new Committee("VF", "Utvalg med valideringsfeil");
        constraintViolations = validator.validate(committee2);
        assertThat(1, is(equalTo(constraintViolations.size())));

        committee2.setName("UUV");
        constraintViolations = validator.validate(committee2);
        assertThat(0, is(equalTo(constraintViolations.size())));
    }
}

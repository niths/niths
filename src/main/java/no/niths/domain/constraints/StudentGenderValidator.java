package no.niths.domain.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for the gender attribute in Student
 * 
 * <p>
 * Valid values is M, F or null if gender is not provided
 * </p>
 *
 */
public class StudentGenderValidator implements ConstraintValidator<StudentGender, Character> {
 
	@Override
	public void initialize(StudentGender number) {
	}
 
	/**
	 * Valid values is M(male), F(emale) or null
	 */
	@Override
	public boolean isValid(Character studentSex, ConstraintValidatorContext context) {
		
		if (studentSex == null || studentSex == 'M' || studentSex == 'F')
			return true;
		
		return false;
	}

}
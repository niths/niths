package no.niths.domain.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
 
public class WeekdayValidator implements ConstraintValidator<Weekday, String> {
 
	private String[] days = {"monday", "tuesday", "wednesday", "thursday", "friday"};
	@Override
	public void initialize(Weekday day) {
	}
 

	/**
	 * Valid values is M(male), F(emale) or null
	 */
	@Override
	public boolean isValid(String day, ConstraintValidatorContext context) {
		
		if(day == null){
			return true;
		}
		for (int i = 0; i < days.length; i++){
			if(day.equalsIgnoreCase(days[i])){
				return true;				
			}
		}
		
		return false;
	}

}
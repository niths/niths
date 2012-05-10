package no.niths.domain.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Defining the @StudentGender annotation
 *
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StudentGenderValidator.class)
public @interface StudentGender {
    String message() default "Must be M(ale) or F(emale)";
 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
 
}
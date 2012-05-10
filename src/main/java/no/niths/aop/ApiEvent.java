package no.niths.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Defines the @ApiEvent annotation
 * 
 * To use: @ApiEvent(title = "Some title")
 * 
 * To add more attributes, simply add new ones:
 * public @interface ApiEvent{
 *         String title();
 *         String description();
 * }
 * 
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiEvent {
    String title();
}

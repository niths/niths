package no.niths.test.common;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTest {

	//Add this to the class
	//Change LoggerTest.class to the class your are working with
	private static final Logger logger = LoggerFactory
	.getLogger(LoggerTest.class); // Replace with test class
	
	/**
	 *		1 – FATAL refers to critical events that will lead to the project terminating completely.
	 *		2 – ERROR refers to any sort of exception, even non-FATAL ones.
	 *		3 – WARN refers to warnings; not exceptions but situations that could be harmful.
	 *		4 – INFO refers to information that shows how the project operates and its ongoing progress. 
	 *			It is a rather rough look at the application’s progress but helps you see what’s going on.
	 *		5 – DEBUG is the most detailed level of logging and shows all events that have occurred. 
	 *			This is the most useful manner in which to debug the sequence of events in an application.
	 *
	 *  	Use the loggback.xml file to set the logging levels/properties
	 */
	
	
	@Test
	public void testLogger() {
		
		logger.info("info");
		
		logger.warn("warn");
		
		logger.error("error");
		
		logger.debug("debug");
		
	}
}

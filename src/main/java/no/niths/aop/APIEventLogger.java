package no.niths.aop;

import java.util.GregorianCalendar;

import no.niths.common.ValidationHelper;
import no.niths.domain.APIEvent;
import no.niths.services.interfaces.APIEventService;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * Saves API events to API event feed
 *
 */
@Aspect
@Component
public class APIEventLogger {
	private static final Logger logger = LoggerFactory
			.getLogger(APIEventLogger.class);
	
	@Autowired
	private APIEventService service;
 
	/**
	 * Executes after all public methods annotated with @ApiEvent
	 * 
	 * @param pjp The method parameter
	 * @param apiEvent The annotation
	 * @throws Throwable
	 */
	@After("execution(public * *(..)) && @annotation(apiEvent)")
	public void saveAPIEvent(JoinPoint pjp, ApiEvent apiEvent) throws Throwable {
		logger.debug("onAPICreateEvent() intercepted call");
		//Get the title of the apiEvent
		String title = apiEvent.title();
		if(pjp.getArgs().length == 1){
			loggAPIEvent(title, pjp.getArgs()[0]);
		}else{
			logger.debug("Parameter list longer then 1 or is 0");
			for ( Object object : pjp.getArgs()) {
				logger.debug(object.toString());
			}	
		}
	}
	
	//Persists an api event to DB
	private void loggAPIEvent(String title, Object obj){
		logger.debug("Saving api event to db: " + title + ":" + obj.toString());

		APIEvent event = new APIEvent(title, obj.toString(), new GregorianCalendar());
		if(ValidationHelper.hasObjectValidAttributes(event)){
			service.create(event);			
		}else{
			logger.debug("Object did not pass validation!");
		}
	}
}

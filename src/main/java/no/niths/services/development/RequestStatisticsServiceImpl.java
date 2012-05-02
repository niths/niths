package no.niths.services.development;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.niths.domain.development.Application;
import no.niths.services.development.interfaces.ApplicationService;
import no.niths.services.development.interfaces.RequestStatisticsService;

@Service
public class RequestStatisticsServiceImpl implements RequestStatisticsService{

    private static final Logger logger = LoggerFactory
            .getLogger(RequestStatisticsServiceImpl.class);
    
	@Autowired
	private ApplicationService appService;

	/**
	 * Simple example method for tracking app statistics
	 * 
	 * @param app the application that holds the request
	 */
	@Override
	public void registerRequest(Application app) {
		if(app.getRequests() != null){
        	app.setRequests(app.getRequests() + 1);        	
        } else {
        	app.setRequests(new Long(1));
        }
		logger.debug("App counter upped! " + app.getTitle() + " - " + app.getRequests());
        appService.update(app);
	}

}

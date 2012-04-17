package no.niths.services.auth;

import no.niths.services.auth.interfaces.KeyGeneratorService;
import no.niths.services.developing.interfaces.ApplicationService;
import no.niths.services.developing.interfaces.DeveloperService;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * Service for generating and developer and application keys
 * 
 */
@Service
public class KeyGeneratorServiceImpl implements KeyGeneratorService {

	private static final Logger logger = LoggerFactory
			.getLogger(KeyGeneratorServiceImpl.class);
	
	private final static int DEVELOPER = 0;
	private final static int APPLICATION = 1;

	@Autowired
	private DeveloperService developerService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Override
	public String generateDeveloperKey() {
		logger.debug("Generating developer key");
		return generateKey(DEVELOPER);
	}

	@Override
	public String generateApplicationKey() {
		logger.debug("Generating application key");
		return generateKey(APPLICATION);
	}
	
	/**
	 * Generates keys
	 * @param domain app or dev
	 * @return key as a string
	 */
	private String generateKey(int domain){
		boolean found = false;
		String key = "";
		while(!found){
			key = RandomStringUtils.randomAlphanumeric(10);
			switch (domain) {
			case DEVELOPER:
				if(developerService.getDeveloperByDeveloperKey(key) == null){
					found = true;
				}				
				break;
			case APPLICATION:
				if(applicationService.getByApplicationKey(key, false) == null){
					found = true;
				}
				break;
			}
		}
		return key;
	}
}

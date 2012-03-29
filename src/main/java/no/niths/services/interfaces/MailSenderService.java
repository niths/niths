package no.niths.services.interfaces;

import no.niths.domain.Application;
import no.niths.domain.Developer;

/**
 * Service Class for sending emails
 *
 */
public interface MailSenderService {
	
	/**
	 * Sends a email
	 * 
	 * @param to email to
	 * @param from email from
	 * @param subject 
	 * @param body
	 * 
	 */
	void composeAndSend(String to, String from, String subject, String body);
	
	void sendDeveloperRegistratedConfirmation(Developer dev);
	
	void sendDeveloperAddedAppConfirmation(Developer dev, Application app);
	
	void sendDeveloperEnabledConfirmation(Developer dev);
	
	void sendApplicationEnabledConfirmation(Developer dev, Application app);

}

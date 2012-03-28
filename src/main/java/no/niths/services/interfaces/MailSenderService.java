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
	public void composeAndSend(String to, String from, String subject, String body);
	
	boolean sendDeveloperRegistratedConfirmation(Developer dev);
	
	boolean sendDeveloperAddedAppConfirmation(Developer dev, Application app);
	
	boolean sendDeveloperEnabledConfirmation(Developer dev);
	
	boolean sendApplicationEnabledConfirmation(Developer dev, Application app);

}

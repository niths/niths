package no.niths.services.interfaces;

import no.niths.domain.developing.Application;
import no.niths.domain.developing.Developer;

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
	
	/**
	 * Sends developer a confirmation email when a developer
	 * is registered
	 * 
	 * @param dev the developer to send an email
	 */
	void sendDeveloperRegistratedConfirmation(Developer dev);
	
	/**
	 * Sends developer an email when an app has been added
	 * 
	 * @param dev Developer to send an email
	 * @param app application that has been added
	 */
	void sendDeveloperAddedAppConfirmation(Developer dev, Application app);
	
	/**
	 * Sends a developer an email with confirmation when
	 * the developer has been enabled
	 * 
	 * @param dev the developer that has been enabled
	 */
	void sendDeveloperEnabledConfirmation(Developer dev);
	
	/**
	 * Sends an email to the developer when an application has
	 * been enabled
	 * 
	 * @param dev the developer to send an email to
	 * @param app the application that has been enabled
	 */
	void sendApplicationEnabledConfirmation(Developer dev, Application app);

}

package no.niths.services.interfaces;
/**
 * Service Class for sending emails
 *
 */
public interface MailSenderService {
	
	public boolean composeAndSend(String email);

}

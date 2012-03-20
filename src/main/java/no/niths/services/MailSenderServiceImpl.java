package no.niths.services;

import no.niths.common.AppConstants;
import no.niths.domain.Developer;
import no.niths.services.auth.AuthenticationServiceImpl;
import no.niths.services.interfaces.MailSenderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
/**
 * Service Class for sending emails
 *
 */
@Service
public class MailSenderServiceImpl implements MailSenderService {

	private static final Logger logger = LoggerFactory
			.getLogger(MailSenderServiceImpl.class);
	
	@Autowired(required=false)
	private MailSender mailSender;
	
	private SimpleMailMessage composeMail(String to, String from, String subject, String body){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from); //Email from
		message.setTo(to); //Email to
		message.setSubject(subject); //Email subject
		message.setText(body); //Email message(body)
		return message;
	}
	
	@Override
	public void composeAndSend(String to, String from, String subject, String body) {
		sendMessage(composeMail(to, from, subject, body));
	}
	

	@Override
	public boolean sendDeveloperEmail(Developer developer) {
		String subject = "Registration success, verification needed";
		String body = "Your developer-token is: " + developer.getDeveloperToken();
		body += "<br/>" + "click me to verify \n er dette på egen linje?";
		return (sendMessage(composeMail(developer.getEmail(), AppConstants.NITHS_EMAIL, subject, body)));
		
	}

	@Async
	private boolean sendMessage(SimpleMailMessage message){
		try{
			if(mailSender != null){
				mailSender.send(message);
				return true;
			}
			logger.warn("Could not send mail, mailsender is null");		
		}catch(MailException me){
			me.printStackTrace();
			logger.warn("MailException! Could not send mail");
		}
		return false;
	}
}

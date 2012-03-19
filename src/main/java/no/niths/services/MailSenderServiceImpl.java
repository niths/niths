package no.niths.services;

import no.niths.domain.Developer;
import no.niths.services.interfaces.MailSenderService;

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
	public void sendDeveloperEmail(Developer developer) {
		// TODO Auto-generated method stub
		
	}

	@Async
	private boolean sendMessage(SimpleMailMessage message){
		try{
			if(mailSender != null){
				mailSender.send(message);
				return true;
			}
		}catch(MailException me){
			//false
		}
		return false;
	}
}

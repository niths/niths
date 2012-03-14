package no.niths.services;

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
	
	private SimpleMailMessage composeMail(String email){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("niths@nith.no");
		message.setTo(email);
		message.setSubject("Hei du!");
		message.setText("hei, dette er en tekst");
		return message;
	}
	
	@Override
	public boolean composeAndSend(String email) {
		if(sendMessage(composeMail(email))){
			return true;
		}
		return false;
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

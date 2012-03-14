package no.niths.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import no.niths.services.interfaces.MailSenderService;

@Service
public class MailSenderServiceImpl implements MailSenderService {

	@Autowired
	private MailSender mailSender;
	
	
	@Override
	public void sendAMail(String email) {
		SimpleMailMessage message = new SimpleMailMessage();
		 
		message.setFrom("niths@nith.no");
		message.setTo(email);
		message.setSubject("Hei du!");
		message.setText("hei, dette er en tekst");
		mailSender.send(message);
		
	}

}

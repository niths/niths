package no.niths.services;

import javax.mail.internet.MimeMessage;

import no.niths.common.AppConstants;
import no.niths.common.EmailTexts;
import no.niths.domain.developing.Application;
import no.niths.domain.developing.Developer;
import no.niths.services.interfaces.MailSenderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
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
	
	@Autowired(required = false)
	private JavaMailSender javaMailSender;
	
	//Composes a simple mail (No html or attachements)
	private SimpleMailMessage composeMail(String to, String from, String subject, String body){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from); //Email from
		message.setTo(to); //Email to
		message.setSubject(subject); //Email subject
		message.setText(body); //Email message(body)
		return message;
	}
	
	//Prepares a MimeMessage (html and attachments)
	private MimeMessagePreparator prepare(final String to,final String from,final String subject,final String body) {
		 MimeMessagePreparator preparator = new MimeMessagePreparator() {
	            public void prepare(MimeMessage mimeMessage) throws Exception {
	               MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
	               message.setTo(to);
	               message.setFrom(from);
	               message.setSubject(subject);
	               message.setText(body, true);
	            }
	         };
	         
	         return preparator;
	}
	
	/**
	 * Sends a developer an email with a enable dev link
	 */
	public void sendDeveloperRegistratedConfirmation(Developer dev){
		String subject = "Hi " + dev.getName()+ ". Registration success, verification needed";
		sendMimeMessage(prepare(dev.getEmail(), AppConstants.NITHS_EMAIL, subject, EmailTexts.getDeveloperConfirmationBody(dev)));
	}
	
	public void sendDeveloperAddedAppConfirmation(Developer dev, Application app){
		String subject = "Hi " + dev.getName()+ ". Your app has been registrated";
		sendMimeMessage(prepare(dev.getEmail(), AppConstants.NITHS_EMAIL, subject, EmailTexts.getAddedAppToDevelioperBody(app)));
	}
	
	public void sendDeveloperEnabledConfirmation(Developer dev){
		String subject = "Hi " + dev.getName()+ ". Your are now enabled!";
		sendMimeMessage(prepare(dev.getEmail(), AppConstants.NITHS_EMAIL, subject, EmailTexts.getDeveloperEnabledBody(dev)));
	}
	
	@Override
	public void sendApplicationEnabledConfirmation(Developer dev,
			Application app) {
		String subject = "Hi " + dev.getName()+ ". Your app now enabled!";
		sendMimeMessage(prepare(dev.getEmail(), AppConstants.NITHS_EMAIL, subject, EmailTexts.getApplicationEnabledBody(dev, app)));
	}
	
	
	@Override
	public void composeAndSend(String to, String from, String subject, String body) {
		sendMessage(composeMail(to, from, subject, body));
	}

	@Async
	private void sendMimeMessage(MimeMessagePreparator message){
		try{
			if(javaMailSender != null){
				javaMailSender.send(message);
			}else{
				logger.warn("Could not send mail, mailsender is null");	
			}
		}catch(MailException me){
			me.printStackTrace();
			logger.warn("MailException! Could not send mail");
		}
	}
	@Async
	private void sendMessage(SimpleMailMessage message){
		try{
			if(mailSender != null){
				mailSender.send(message);
			}else {
				logger.warn("Could not send mail, mailsender is null");	
			}
		}catch(MailException me){
			me.printStackTrace();
			logger.warn("MailException! Could not send mail");
		}
	}


}

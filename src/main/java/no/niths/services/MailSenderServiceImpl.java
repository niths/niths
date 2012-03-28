package no.niths.services;

import javax.mail.internet.MimeMessage;

import no.niths.common.AppConstants;
import no.niths.common.EmailTexts;
import no.niths.domain.Application;
import no.niths.domain.Developer;
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
	public boolean sendDeveloperRegistratedConfirmation(Developer dev){
		String subject = "Hi " + dev.getName()+ ". Registration success, verification needed";
		return (sendMimeMessage(prepare(dev.getEmail(), AppConstants.NITHS_EMAIL, subject, EmailTexts.getDeveloperConfirmationBody(dev))));
	}
	
	public boolean sendDeveloperAddedAppConfirmation(Developer dev, Application app){
		String subject = "Hi " + dev.getName()+ ". Your app has been registrated";
		return (sendMimeMessage(prepare(dev.getEmail(), AppConstants.NITHS_EMAIL, subject, EmailTexts.getAddedAppToDevelioperBody(app))));
	}
	
	public boolean sendDeveloperEnabledConfirmation(Developer dev){
		String subject = "Hi " + dev.getName()+ ". Your are now enabled!";
		return (sendMimeMessage(prepare(dev.getEmail(), AppConstants.NITHS_EMAIL, subject, EmailTexts.getDeveloperEnabledBody(dev))));
	}
	
	@Override
	public boolean sendApplicationEnabledConfirmation(Developer dev,
			Application app) {
		String subject = "Hi " + dev.getName()+ ". Your app now enabled!";
		return (sendMimeMessage(prepare(dev.getEmail(), AppConstants.NITHS_EMAIL, subject, EmailTexts.getApplicationEnabledBody(dev, app))));
	}
	
	
	@Override
	public void composeAndSend(String to, String from, String subject, String body) {
		sendMessage(composeMail(to, from, subject, body));
	}

	@Async
	private boolean sendMimeMessage(MimeMessagePreparator message){
		try{
			if(javaMailSender != null){
				javaMailSender.send(message);
				return true;
			}
			logger.warn("Could not send mail, mailsender is null");		
		}catch(MailException me){
			me.printStackTrace();
			logger.warn("MailException! Could not send mail");
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
			logger.warn("Could not send mail, mailsender is null");		
		}catch(MailException me){
			me.printStackTrace();
			logger.warn("MailException! Could not send mail");
		}
		return false;
	}


}

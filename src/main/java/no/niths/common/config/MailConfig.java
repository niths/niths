package no.niths.common.config;

import java.util.Properties;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

	@Inject
	private Environment environment;

	/**
	 * The Java Mail sender.
	 */
	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setDefaultEncoding("UTF-8");
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("nithscommunity@gmail.com");
		mailSender.setPassword("nithsproject123");
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		mailSender.setJavaMailProperties(properties);
		return mailSender;
	}
//	@Bean
//	public JavaMailSender mailSender() {
//		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//		mailSender.setDefaultEncoding("UTF-8");
//		mailSender.setHost(environment.getProperty("mail.host"));
//		mailSender.setPort(environment.getProperty("mail.port", Integer.class, 25));
//		mailSender.setUsername(environment.getProperty("mail.username"));
//		mailSender.setPassword(environment.getProperty("mail.password"));
//		Properties properties = new Properties();
//		properties.put("mail.smtp.auth", environment.getProperty("mail.smtp.auth", Boolean.class, false));
//		properties.put("mail.smtp.starttls.enable", environment.getProperty("mail.smtp.starttls.enable", Boolean.class, false));
//		mailSender.setJavaMailProperties(properties);
//		return mailSender;
//	}
}

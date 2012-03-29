package no.niths.common.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
/**
 * Configuration class for mail service
 *
 */
@Configuration
public class MailConfig {

	@Value("${smtp.host}")
	private String host;
	
	@Value("${smtp.port}")
	private String port;
	
	@Value("${smtp.username}")
	private String username;
	
	@Value("${smtp.password}")
	private String password;
	
	/**
	 * The Java Mail sender.
	 */
	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setDefaultEncoding("UTF-8");
		mailSender.setHost(host);
		mailSender.setPort(Integer.parseInt(port));
		mailSender.setUsername(username);
		mailSender.setPassword(password);
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		mailSender.setJavaMailProperties(properties);
		return mailSender;
	}
}

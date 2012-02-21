package no.niths.common.config;

import javax.inject.Inject;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.google.connect.GoogleConnectionFactory;


@Configuration
public class SocialConfig {

//	@Autowired
//	private Environment environment;
//
//	@Autowired
//	private DataSource dataSource;
	
	/**
	 * ADD TO PROPERTIES FILE:
	 * 
	 * 
	 * google.clientId=1052169777876.apps.googleusercontent.com
	 * google.clientSecret=i4QqNZH8lhD5gtBU114yeZIw
	 * 
	 * 
	 */
	@Value("${google.clientSecret}")
	private String secret;
	
	@Value("${google.clientId}")
	private String clientId;

	/**
	 * When a new provider is added to the app, register its {@link ConnectionFactory} here.
	 * @see GoogleConnectionFactory
	 */
	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
//		registry.addConnectionFactory(new GoogleConnectionFactory("1052169777876.apps.googleusercontent.com",
//				"i4QqNZH8lhD5gtBU114yeZIw"));
		registry.addConnectionFactory(new GoogleConnectionFactory(clientId, secret));
//		registry.addConnectionFactory(new GoogleConnectionFactory(environment.getProperty(clientId),
//				environment.getProperty(secret)));
		return registry;
	}


}

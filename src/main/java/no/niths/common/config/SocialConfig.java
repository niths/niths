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
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.connect.GoogleConnectionFactory;


@Configuration
public class SocialConfig {


	@Autowired
	private DataSource dataSource;
	
//	@Inject
//	private TextEncryptor textEncryptor;
	
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
	
//	/**
//	 * The shared store for users' connection information.
//	 * Uses a RDBMS-based store accessed with Spring's JdbcTemplate.
//	 * The returned repository encrypts the data using the configured {@link TextEncryptor}.
//	 */
//	@Bean
//	public UsersConnectionRepository usersConnectionRepository() {
//		return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator(),Encryptors.noOpText());
//	}
//	
//	/**
//	 * Request-scoped data access object providing access to the current user's connections.
//	 */
//	@Bean
//	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
//	public ConnectionRepository connectionRepository() {
//	    User user = SecurityContext.getCurrentUser();
//	    return usersConnectionRepository().createConnectionRepository(user.getId());
//	}
//
//	
//	/**
//	 * A proxy to a request-scoped object representing the current user's primary Facebook account.
//	 * @throws NotConnectedException if the user is not connected to facebook.
//	 */
//	@Bean
//	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
//	public Google google() {
//	    return connectionRepository().getPrimaryConnection(Google.class).getApi();
//	}

}

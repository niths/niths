package no.niths.common.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.google.connect.GoogleConnectionFactory;


@Configuration
public class SocialConfig {

	@Autowired
	private Environment environment;

	@Autowired
	private DataSource dataSource;

	/**
	 * When a new provider is added to the app, register its {@link ConnectionFactory} here.
	 * @see GoogleConnectionFactory
	 */
	@Bean
	public ConnectionFactoryLocator connectionFactoryLocator() {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(new GoogleConnectionFactory("1052169777876.apps.googleusercontent.com",
				"i4QqNZH8lhD5gtBU114yeZIw"));
//		registry.addConnectionFactory(new GoogleConnectionFactory(environment.getProperty("google.clientId"),
//				environment.getProperty("google.clientSecret")));
		return registry;
	}
	@Bean
    public UsersConnectionRepository usersConnectionRepository() {
        return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator(), 
        	Encryptors.noOpText());
    }
	
	@Bean
    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
    public ConnectionRepository connectionRepository(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
        }
        return usersConnectionRepository().createConnectionRepository(authentication.getName());
    }
	
	
//	/**
//	 * Singleton data access object providing access to connections across all users.
//	 */
//	@Bean
//	public UsersConnectionRepository usersConnectionRepository() {
//		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
//				connectionFactoryLocator(), Encryptors.noOpText());
//		repository.setConnectionSignUp(new SimpleConnectionSignUp());
//		return repository;
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
//	/**
//	 * A proxy to a request-scoped object representing the current user's primary Facebook account.
//	 * @throws NotConnectedException if the user is not connected to facebook.
//	 */
//	@Bean
//	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)	
//	public Google google() {
//	    return connectionRepository().getPrimaryConnection(Google.class).getApi();
//	}
	
//	/**
//	 * The Spring MVC Controller that allows users to sign-in with their provider accounts.
//	 */
//	@Bean
//	public ProviderSignInController providerSignInController() {
//		return new ProviderSignInController(connectionFactoryLocator(), usersConnectionRepository(),
//				new SimpleSignInAdapter());
//	}
	

}

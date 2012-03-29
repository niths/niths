package no.niths.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.google.connect.GoogleConnectionFactory;

/**
 * Configuration class for Spring Social
 *
 */

@Configuration
public class SocialConfig {
	
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
		registry.addConnectionFactory(new GoogleConnectionFactory(clientId, secret));
		return registry;
	}
	


}

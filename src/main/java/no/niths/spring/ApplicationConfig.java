package no.niths.spring;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
public class ApplicationConfig{
	

	@Bean
	public static PropertyPlaceholderConfigurer properties(){
		final PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		final Resource[] resources = new ClassPathResource[ ] { new ClassPathResource( "persistence.properties")};
		ppc.setLocations( resources );
		ppc.setIgnoreUnresolvablePlaceholders( true );
		
		return ppc;
	
	}
	
}

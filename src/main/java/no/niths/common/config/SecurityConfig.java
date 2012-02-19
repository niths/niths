package no.niths.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
//@ComponentScan( "no.niths.application.rest" )
@ImportResource( { "classpath*:springSecurityConfig.xml" } )
public class SecurityConfig{
	
	public SecurityConfig(){
		super();
	}

}

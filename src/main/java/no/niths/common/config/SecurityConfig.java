package no.niths.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 
 * Class that handles the security configuration
 * Spring Security 3 does not fully support Java configuration,
 * <p>
 * @see springSecurityConfig.xml for configuration
 * </p>
 *
 */

@Configuration
@ComponentScan( "no.niths.security" )
@ImportResource( { "classpath*:springSecurityConfig.xml" } )
public class SecurityConfig{
    
    public SecurityConfig(){
        super();
    }

}

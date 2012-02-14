package no.niths.common.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
@ComponentScan( {TestAppConfig.BASE_PACKAGE } )
public class TestAppConfig {
//"no.niths.services","no.niths.common.config"
    public static final String BASE_PACKAGE      = "no.niths",
                               PERSISTENCE_PROPS = "test-persistence.properties";
    @Bean
    public static PropertyPlaceholderConfigurer properties(){
        final PropertyPlaceholderConfigurer ppc =
                new PropertyPlaceholderConfigurer();
        FileSystemResource fsr = new FileSystemResource(System.getenv("CREDENTIAL_PATH")+"/"+PERSISTENCE_PROPS);
        ppc.setLocation(fsr);  
        ppc.setIgnoreUnresolvablePlaceholders(true);
        return ppc;
    }
}
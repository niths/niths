package no.niths.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * Configuration class for REST
 *
 */
@Configuration
@ComponentScan({ AppConfig.REST_PACKAGE, AppConfig.EXTERNAL_PACKAGE })
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    public WebConfig() {
        super();
    }
    
//    To add an interceptor, create a class that extends HandleInterceptorAdapter
//    and implements one (or more) of the methods preHandle, postHandle or
//    afterCompletion. Then uncomment under and add the class to the
//    interceptor registry
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new YourInterceptor());
//    }
    
}
package no.niths.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(AppConfig.BASE_PACKAGE)
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter{

    public WebConfig(){
        super();
    }
}
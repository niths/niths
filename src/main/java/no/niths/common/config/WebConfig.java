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
    
//    @Bean
//    public XStreamMarshaller xstreamMarshaller(){
//        final XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
//        xStreamMarshaller.setAutodetectAnnotations( true );
//        // xStreamMarshaller.setSupportedClasses( new Class[ ] { User.class, Privilege.class } );
//        xStreamMarshaller.setAnnotatedClass( Course.class );
//        
//        // this.xstreamMarshaller().getXStream().addDefaultImplementation( java.util.HashSet.class, PersistentSet.class );
//        
//        return xStreamMarshaller;
//    }
//    @Bean
//    public MarshallingHttpMessageConverter marshallingHttpMessageConverter(){
//        final MarshallingHttpMessageConverter marshallingHttpMessageConverter = new MarshallingHttpMessageConverter();
//        marshallingHttpMessageConverter.setMarshaller( xstreamMarshaller() );
//        marshallingHttpMessageConverter.setUnmarshaller( xstreamMarshaller() );
//        
//        return marshallingHttpMessageConverter;
//    }
//    
//    @Override
//    public void configureMessageConverters( final List< HttpMessageConverter< ? >> converters ){
//        super.configureMessageConverters( converters );
//        
//        converters.add( marshallingHttpMessageConverter() );
//    }
}
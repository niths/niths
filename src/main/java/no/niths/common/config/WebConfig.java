package no.niths.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(AppConfig.REST_PACKAGE)
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	public WebConfig() {
		super();
	}
	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new LogInInterceptor());
//	}
	

//	@Bean
//	public ContentNegotiatingViewResolver contentNegotiatingViewResolver() {
//		ContentNegotiatingViewResolver ctnvr = new ContentNegotiatingViewResolver();
//
//		Map<String,String> mediaTypes = new HashMap<String, String>();
//		
//		mediaTypes.put("xml", MediaType.APPLICATION_XML_VALUE);
//		mediaTypes.put("json", MediaType.APPLICATION_JSON_VALUE);
//		
//		ctnvr.setMediaTypes(mediaTypes);
//		ctnvr.setDefaultContentType(MediaType.APPLICATION_JSON);
//		ctnvr.setFavorPathExtension(true);
//		ctnvr.setOrder(1);
//		
//		List<ViewResolver> resolver = new ArrayList<ViewResolver>(); 
//		resolver.add(new BeanNameViewResolver());
//		ctnvr.setViewResolvers(resolver);
//		
//		
//		List<View> defaultViews = new ArrayList<View>();
//		MarshallingView marsh =	new MarshallingView();
//		XStreamMarshaller xx = new XStreamMarshaller();
//		xx.setAutodetectAnnotations(true);
//		marsh.setMarshaller(xx);
//		
//		defaultViews.add(marsh);
//		
//		MappingJacksonJsonView mapper = new MappingJacksonJsonView();
//		
//		
//		defaultViews.add(mapper);
//		ctnvr.setDefaultViews(defaultViews);
//		
//		ctnvr.setIgnoreAcceptHeader(true);
//		return null;
//
//	}

	// @Bean
	// public XStreamMarshaller xstreamMarshaller(){
	// final XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
	// xStreamMarshaller.setAutodetectAnnotations( true );
	// // xStreamMarshaller.setSupportedClasses( new Class[ ] { User.class,
	// Privilege.class } );
	// xStreamMarshaller.setAnnotatedClass( Course.class );
	//
	// // this.xstreamMarshaller().getXStream().addDefaultImplementation(
	// java.util.HashSet.class, PersistentSet.class );
	//
	// return xStreamMarshaller;
	// }
	// @Bean
	// public MarshallingHttpMessageConverter marshallingHttpMessageConverter(){
	// final MarshallingHttpMessageConverter marshallingHttpMessageConverter =
	// new MarshallingHttpMessageConverter();
	// marshallingHttpMessageConverter.setMarshaller( xstreamMarshaller() );
	// marshallingHttpMessageConverter.setUnmarshaller( xstreamMarshaller() );
	//
	// return marshallingHttpMessageConverter;
	// }
	//
	// @Override
	// public void configureMessageConverters( final List< HttpMessageConverter<
	// ? >> converters ){
	// super.configureMessageConverters( converters );
	//
	// converters.add( marshallingHttpMessageConverter() );
	// }
}
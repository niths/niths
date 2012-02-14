package no.niths.application.rest;

import org.junit.Before;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public abstract class BaseWebApplicationContextTests implements
		ApplicationContextAware {

	// this servlet is going to be instantiated by ourselves
	// so that we can test the servlet behaviour w/o actual web container
	// deployment
	protected DispatcherServlet servlet;

	// we need to get at the context already loaded via the
	// @ContextConfiguration annotation.
//	@Autowired
	protected ApplicationContext appCtx;

	
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		appCtx = applicationContext;
	}

	/**
	 * using @Before is convenient, but with JUnit these annotated methods must
	 * be public (not like e.g. testNG) so be aware that no "secrets" are being
	 * set/got in these init-style methods ;_)! !
	 */	
	@Before
	public void initDispatcherServlet() {
		servlet = new DispatcherServlet() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 7298606637552795995L;

			@Override
			protected WebApplicationContext createWebApplicationContext(
					WebApplicationContext parent) throws BeansException {

				GenericWebApplicationContext gwac = new GenericWebApplicationContext();
				gwac.setParent(appCtx);
				gwac.refresh();
				return gwac;
			}
		};
	}
}
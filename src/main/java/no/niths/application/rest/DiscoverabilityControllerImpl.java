package no.niths.application.rest;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.interfaces.DiscoverabilityController;
import no.niths.common.AppConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;
/**
 * Simple discoverability class that handles GET requests to the ROOT uri
 * <p>
 * Returns all valid domain in header
 *
 */
@Controller
public class DiscoverabilityControllerImpl implements DiscoverabilityController{

	private static final Logger logger = LoggerFactory
			.getLogger(DiscoverabilityControllerImpl.class);
	
	private final String[] domains = {AppConstants.ACCESS_FIELDS, 
										AppConstants.ACCESS_POINTS,
										AppConstants.ADMIN,
										AppConstants.ADMIN_DEV,
										AppConstants.API_EVENTS,
										AppConstants.APPLICATIONS,
										AppConstants.AUTH,
										AppConstants.COMMITTEES,
										AppConstants.COURSES,
										AppConstants.CONSOLES,
										AppConstants.COURSES,
										AppConstants.DEVELOPERS,
										AppConstants.EVENTS,
										AppConstants.EXAMS,
										AppConstants.FADDER,
										AppConstants.FADDER_GROUPS,
										AppConstants.FEEDS,
										AppConstants.GAMES,
										AppConstants.LOCATIONS,
										AppConstants.ROLES,
										AppConstants.ROOMS,
										AppConstants.STUDENTS,
										AppConstants.SUBJECTS};
	
	@Override
	@RequestMapping( value = "/",method = RequestMethod.GET )
	@ResponseStatus( value = HttpStatus.NO_CONTENT )
	public void root(HttpServletRequest request, HttpServletResponse response) {
		String rootUri = request.getRequestURL().toString();
		logger.debug("ROOTURI: " + rootUri);
		
		String [] uris = new String[domains.length];
		for (int i = 0; i < domains.length; i++){
			URI uri = new UriTemplate( "{rootUri}{resource}" ).expand( rootUri, domains[i] );
			uris[i] = uri.toASCIIString();
		}
		
		response.addHeader( "link", gatherLinkHeaders( uris ) );
	}
	
	private String gatherLinkHeaders( String... uris ){
		StringBuilder linkHeaderValue = new StringBuilder();
		for(String uri : uris ){
			linkHeaderValue.append( uri );
			linkHeaderValue.append( " ; " );
		}
		return linkHeaderValue.substring( 0, linkHeaderValue.length() - 2 ).toString();
	}

}

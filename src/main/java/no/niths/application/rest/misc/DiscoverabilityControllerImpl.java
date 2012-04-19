package no.niths.application.rest.misc;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.interfaces.DiscoverabilityController;
import no.niths.common.AppNames;

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
	
	private final String[] domains = {AppNames.ACCESS_FIELDS,
										AppNames.ACCESS_POINTS,
										AppNames.ADMIN,
										AppNames.ADMIN_DEV,
										AppNames.API_EVENTS,
										AppNames.APPLICATIONS,
										AppNames.AUTH,
										AppNames.COMMITTEES,
										AppNames.COURSES,
										AppNames.CONSOLES,
										AppNames.COURSES,
										AppNames.DEVELOPERS,
										AppNames.EVENTS,
										AppNames.EXAMS,
										AppNames.FADDER,
										AppNames.FADDER_GROUPS,
										AppNames.FEEDS,
										AppNames.GAMES,
										AppNames.LOCATIONS,
										AppNames.ROLES,
										AppNames.ROOMS,
										AppNames.STUDENTS,
										AppNames.SUBJECTS};
	
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

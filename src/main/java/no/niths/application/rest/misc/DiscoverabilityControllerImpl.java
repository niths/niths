package no.niths.application.rest.misc;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.RESTConstants;
import no.niths.application.rest.misc.interfaces.DiscoverabilityController;
import no.niths.common.AppNames;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Simple discoverability class that handles GET requests to the ROOT uri
 * <p>
 * Returns all valid domain in header
 *
 */
@Controller
public class DiscoverabilityControllerImpl implements DiscoverabilityController{

    @Override
    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ResponseBody
    public String root(HttpServletRequest req, HttpServletResponse res) {
        res.setContentType(RESTConstants.JSON);

        String json = buildURLs(req.getRequestURL().toString());

        System.err.println(json);
        
        return json;
    }

    private String buildURLs(String rootURL) {
        StringBuilder jsonBuilder = new StringBuilder("[");

        try {
            for (Field field : AppNames.class.getFields()) {

                jsonBuilder.append(String.format(
                        "{ \"url\": \"%s%s\"},",
                        rootURL, field.get(null)));
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return jsonBuilder.deleteCharAt(
                jsonBuilder.length() - 1).append(']').toString();
    }

//@Override
//@RequestMapping( value = "/",method = RequestMethod.GET )
//@ResponseStatus( value = HttpStatus.NO_CONTENT )
//public void root(HttpServletRequest request, HttpServletResponse response) {
//    String rootUri = request.getRequestURL().toString();
//    logger.debug("ROOTURI: " + rootUri);
//    
//    String [] uris = new String[domains.length];
//    for (int i = 0; i < domains.length; i++){
//        URI uri = new UriTemplate( "{rootUri}{resource}" ).expand( rootUri, domains[i] );
//        uris[i] = uri.toASCIIString();
//    }
//    
//    response.addHeader( "link", gatherLinkHeaders( uris ) );
//}
//
//private String gatherLinkHeaders( String... uris ){
//    StringBuilder linkHeaderValue = new StringBuilder();
//    for(String uri : uris ){
//        linkHeaderValue.append( uri );
//        linkHeaderValue.append( " ; " );
//    }
//    return linkHeaderValue.substring( 0, linkHeaderValue.length() - 2 ).toString();
//}

  
//    private final String[] domains = {AppNames.ACCESS_FIELDS,
//                                        AppNames.ACCESS_POINTS,
//                                        AppNames.ADMIN,
//                                        AppNames.ADMIN_DEV,
//                                        AppNames.API_EVENTS,
//                                        AppNames.APPLICATIONS,
//                                        AppNames.AUTH,
//                                        AppNames.COMMITTEES,
//                                        AppNames.COURSES,
//                                        AppNames.CONSOLES,
//                                        AppNames.COURSES,
//                                        AppNames.DEVELOPERS,
//                                        AppNames.EVENTS,
//                                        AppNames.EXAMS,
//                                        AppNames.FADDER,
//                                        AppNames.FADDER_GROUPS,
//                                        AppNames.FEEDS,
//                                        AppNames.GAMES,
//                                        AppNames.LOCATIONS,
//                                        AppNames.ROLES,
//                                        AppNames.ROOMS,
//                                        AppNames.STUDENTS,
//                                        AppNames.SUBJECTS};
//    

}

package no.niths.application.rest;

import org.springframework.http.MediaType;
/**
 * Class for constants used with REST
 */
public class RESTConstants {

    public static final String JSON_AND_XML =MediaType.APPLICATION_JSON_VALUE+"," + MediaType.APPLICATION_XML_VALUE;
    public static final String ACCEPT = "Accept";
    public static final String CONTENT_TYPE = "Content-type";
    public static final String ACCEPT_HEADER = ACCEPT + "= "+ JSON_AND_XML;
    public static final String CONTENT_TYPE_HEADER = CONTENT_TYPE + "=" +JSON_AND_XML;
            

    
}
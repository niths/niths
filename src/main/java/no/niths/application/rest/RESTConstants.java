package no.niths.application.rest;

public class RESTConstants {
    public static final String JSON = "application/json",
                               XML  = "application/xml";
    public static final String JSON_AND_XML = JSON + ", " + XML;
    public static final String ACCEPT = "Accept";
    public static final String CONTENT_TYPE = "Content-type";
    public static final String ACCEPT_HEADER = ACCEPT + "= "+ JSON_AND_XML;
    public static final String CONTENT_TYPE_HEADER = CONTENT_TYPE + "=" +
            JSON_AND_XML;
   
}

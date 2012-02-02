package no.niths.application.rest;

public class RESTConstants {
    public static final String JSON = "application/json",
                               XML  = "application/xml";
    public static final String ACCEPT ="Accept";
    public static final String HEADERS = ACCEPT+"="+ JSON + ", " +XML;
    
    public static final String  [] J_ID = 
    	{"{id}.json", "id/{id}.json","id/{id}"};
}

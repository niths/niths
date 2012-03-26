package no.niths.security;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;
/**
 * Wrapper class for token
 *
 */
@XmlRootElement
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DeveloperToken implements Serializable{ 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1415495457020406132L;
	private String token;
	private String message;
	
	public DeveloperToken(){
		this("Not valid", "Email sendt with instuctions, check your inbox");
	}
	
	public DeveloperToken(String token){
		this.token = token;
	}
	public DeveloperToken(String token, String message){
		this.token = token;
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}

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
public class ApplicationToken implements Serializable{
	
	private static final long serialVersionUID = -1021190003418694035L;
	private String token;
	private String message;
	private String appKey;
	
	public ApplicationToken(){
		this("Not valid");
	}
	
	public ApplicationToken(String token){
		this.token = token;
	}
	public ApplicationToken(String token, String message){
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

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	
	

}

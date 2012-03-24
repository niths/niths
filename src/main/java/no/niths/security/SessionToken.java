package no.niths.security;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * Wrapper class for token
 *
 */
@XmlRootElement
public class SessionToken implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8019110671289257365L;
	private String token;
	
	public SessionToken(){
		token = "Not a valid token provided";
	}
	
	public SessionToken(String token){
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
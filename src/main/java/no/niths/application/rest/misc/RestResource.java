package no.niths.application.rest.misc;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class RestResource implements Serializable {
	private static final long serialVersionUID = 7911809711592253431L;

	private String path;
	private ArrayList<MethodInfo> methods = new ArrayList<MethodInfo>();
	
	public RestResource(String path){
		this.setPath(path);
	}
	
	
	
	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}



	public ArrayList<MethodInfo> getMethods() {
		return methods;
	}
	
	@XmlTransient
	public void addMethod(String values, String method, String headers,
				String reason, String responseCode, String resAuth){
		methods.add(new MethodInfo(values, method, headers, reason, responseCode, resAuth));
	}



	public void setMethods(ArrayList<MethodInfo> methods) {
		this.methods = methods;
	}



	/**
	 * 
	 */
	
	private class MethodInfo{
		
		private String path;
		private String method;
		private String headers;
		private String message;
		private String code;
		private String authorization;
		
		public MethodInfo(String values, String method, String headers,
				String reason, String responseCode, String resAuth) {
			this.path = values;
			this.method = method;
			this.headers = headers;
			this.message = reason;
			this.code = responseCode;
			this.authorization = resAuth;
			
		}
		
		public String getPath() {
			return path;
		}
		
		public void setPath(String values) {
			this.path = values;
		}
		
		public String getMethod() {
			return method;
		}
		
		public void setMethod(String method) {
			this.method = method;
		}
		
		public String getHeaders() {
			return headers;
		}
		
		public void setHeaders(String headers) {
			this.headers = headers;
		}
		
		public String getMessage() {
			return message;
		}
		
		public void setMessage(String reason) {
			this.message = reason;
		}
		
		public String getCode() {
			return code;
		}
		
		public void setCode(String code) {
			this.code = code;
		}
		
		public String getAuthorization() {
			return authorization;
		}
		
		public void setAuthorization(String authorization) {
			this.authorization = authorization;
		}
	}
	

}

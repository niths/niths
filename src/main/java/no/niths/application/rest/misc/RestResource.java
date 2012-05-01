package no.niths.application.rest.misc;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RestResource implements Serializable {
	private static final long serialVersionUID = 7911809711592253431L;

	private String path;
	
	private ArrayList<MethodInfo> methods = new ArrayList<MethodInfo>();
	
	public RestResource(){
		
	}
	
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
	
//	public void addMethod(String values, String method, String headers,
//				String reason, String responseCode, String resAuth){
//		methods.add(new MethodInfo(values, method, headers, reason, responseCode, resAuth));
//	}
//


	public void setMethods(ArrayList<MethodInfo> methods) {
		this.methods = methods;
	}



}

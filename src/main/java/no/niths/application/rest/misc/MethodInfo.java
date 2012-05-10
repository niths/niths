package no.niths.application.rest.misc;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * Class for holding information about a method
 * and the request done toward it
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MethodInfo implements Serializable {

    private static final long serialVersionUID = 1076462441091503957L;
    private String path;
    private String method;
    private String headers;
    private String message;
    private String code;
    private String authorization;
    
    public MethodInfo(){
        
    }

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

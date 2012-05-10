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
public class SessionToken implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = -8019110671289257365L;
    private String token;
    private Long studentId;
    
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
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return token;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    
    

}

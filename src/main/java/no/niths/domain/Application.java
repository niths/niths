package no.niths.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.Email;

import no.niths.common.AppConstants;
import no.niths.domain.constraints.Weekday;

@XmlRootElement
@Entity
@Table(name = AppConstants.APPLICATIONS)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Application implements Serializable {

	@Transient
	private static final long serialVersionUID = 2489514983742481691L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(min = 2, max = 80, message ="The length of the name must be between 2 to 80 letters")
    private String title;
    
    @Column(name = "developer_email")
    @Email
    private String developerEmail;
    
    @Column
    @Size(min = 3, max = 30, message = "Length min = 3, max = 30")
    private String developerName;

	@Column(length=500)
    @Size(max = 500, message ="The length of the description must not exceed 500 letters")
    private String description;
    
    @Column
    @Size(max=200, message = "Url to long, max length = 200")
    private String iconUrl;
    
    @JsonIgnore
	@XmlTransient
    @Column
    private String token;
    
    @JsonIgnore
	@XmlTransient
    @Column
    private Boolean isValid;
    
    public Application(){
    }
    
    public Application(String developerEmail){
    	this.developerEmail = developerEmail;
    }
    public Application(String title, String developerEmail, String description, String iconUrl, String developerName){
    	this.title = title;
    	this.developerEmail = developerEmail;
    	this.description = description;
    	this.iconUrl = iconUrl;
    	this.developerName = developerName;
    }
     
    
    public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	@JsonIgnore
    public boolean isEmpty(){
    	return true;
    }
    
    @Override
    public String toString() {
    	return String.format("[%s][%s][%s][%s][%s]", id, title, description, iconUrl, developerName);
    }
 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
    public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}
	public String getDeveloperEmail() {
		return developerEmail;
	}

	public void setDeveloperEmail(String developerEmail) {
		this.developerEmail = developerEmail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

}

package no.niths.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@Entity
@Table(name = AppConstants.API_EVENTS)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class APIEvent implements Serializable{
	
	@Transient
	private static final long serialVersionUID = -5311081154825173386L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	@Size(min = 1, max = 80, message ="The length of the title must be between 1 and 80 letters")
	private String title;
	
    @Column(length=500)
    @Size(max = 500, message ="The length of the description must not exceed 500 letters")
	private String description;
    
    public APIEvent(){
    	
    }
    
    public APIEvent(String title, String description){
    	this.title = title;
    	this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("[%s][%s][%s]", id, title, description);
	}
    
	
	
	
	

}

package no.niths.domain;

import java.io.Serializable;
import java.sql.Time;

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

@XmlRootElement
@Entity
@Table(name = AppConstants.TOPICS)
public class Topic implements Serializable {

	@Transient
	private static final long serialVersionUID = 3477975219659800316L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(min = 3, max = 80, message ="The length of the name must be between 3 to 80 letters")
    private String name;
    
    @Column(unique = true)
    @Size(min = 2, max = 10, message ="The length of the topic code must be between 2 to 10 letters")
    private String topicCode;
	
    @Column(length=500)
    @Size(max = 500, message ="The length of the description must not exceed 500 letters")
    private String description;
    
    @Column
    private Time startTime;
    
    @Column
    private Time endTime;
    
    public Topic(){
    	//this(null, null, null, null, null);
    }
    
    public Topic(String name, String topicCode, String description, Time startTime, Time endTime){
    	setName(name);
    	setTopicCode(topicCode);
    	setDescription(description);
    	setStartTime(startTime);
    	setEndTime(endTime);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTopicCode() {
		return topicCode;
	}

	public void setTopicCode(String topicCode) {
		this.topicCode = topicCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	
    
	
	

}

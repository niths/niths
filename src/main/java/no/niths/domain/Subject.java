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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import no.niths.common.AppConstants;
import no.niths.domain.constraints.Weekday;

@XmlRootElement
@Entity
@Table(name = AppConstants.SUBJECTS)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Subject implements Serializable {

	@Transient
	private static final long serialVersionUID = 3477975219659800316L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(min = 3, max = 80, message ="The length of the name must be between 3 to 80 letters")
    private String name;
    
    @Column(unique = true, name = "subject_code")
    @Size(min = 2, max = 10, message ="The length of the topic code must be between 2 to 10 letters")
    private String subjectCode;
	
    @Column(length=500)
    @Size(max = 500, message ="The length of the description must not exceed 500 letters")
    private String description;
    
    @Column
    @Weekday
    private String weekday;
    
    @Column(name="room_number")
    @Size(min = 1, max = 10, message ="The length of the room number must be between 2 to 10 letters")
    private String roomNumber;
    
    @Column(name = "start_time")
    @Pattern(regexp = "(^$)|([0-2]{1}[0-9]{1}:[0-9]{2})", message = "Not a valid time")
    private String startTime;
    
    @Column(name = "end_time")
    @Pattern(regexp = "(^$)|([0-2]{1}[0-9]{1}:[0-9]{2})", message = "Not a valid time")
    private String endTime;
    
    public Subject(){
    	//this(null, null, null, null, null);
    }
    
    public Subject(String name, String topicCode, String description, String startTime, String endTime){
    	setName(name);
    	setSubjectCode(topicCode);
    	setDescription(description);
    	setStartTime(startTime);
    	setEndTime(endTime);
    }
    
    @JsonIgnore
    public boolean isEmpty(){
    	return (id == null && name == null && subjectCode == null && description ==  null
    			&& weekday == null && startTime == null && endTime == null);
    }
 
	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
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

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String code) {
		this.subjectCode = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	

	
    
	
	

}

package no.niths.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
    
    @JsonIgnore
   	@XmlTransient
   	@ManyToMany(fetch = FetchType.LAZY, targetEntity= Course.class)
	@JoinTable(name = "courses_subjects", 
		joinColumns = @JoinColumn(name = "subjects_id"), 
		inverseJoinColumns = @JoinColumn(name = "courses_id"))
    @Cascade(CascadeType.ALL)
    private List<Course> courses = new ArrayList<Course>();

    @JsonIgnore
    @XmlTransient
    @OneToMany(fetch = FetchType.LAZY, targetEntity= Exam.class)
    @JoinTable(name = "exam_subjects",
            joinColumns = @JoinColumn(name = "subjects_id"),
            inverseJoinColumns = @JoinColumn(name = "exams_id"))
    @Cascade(CascadeType.ALL)
    private List<Exam> exams = new ArrayList<Exam>();
    
    @ManyToMany(fetch = FetchType.LAZY, targetEntity= Student.class)
    @JoinTable(name = "subjects_tutors",
    joinColumns = @JoinColumn(name = "subjects_id"),
    inverseJoinColumns = @JoinColumn(name = "tutors_id"))
    @Cascade(CascadeType.ALL)
    private List<Student> tutors = new ArrayList<Student>();
    
    public Subject(){
    	this(null, null, null, null, null);
    	setExams(null);
    	setCourses(null);
    	setTutors(null);
    }
    
    public Subject(String name){
    	this(name,null, null, null,null);
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
    
    @Override
    public String toString() {
    	return String.format("[%s][%s][%s]", id, name, description);
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

	@Override
    public boolean equals(Object that) {
    	if(!(that instanceof Subject)) return false;
    	Subject sub = (Subject) that;

        return sub == this ? true : sub.getId() == id
                ? true : false;
    }
	
	@JsonIgnore
	@XmlTransient
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

    @JsonIgnore
    @XmlTransient
    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

	public List<Student> getTutors() {
		return tutors;
	}

	public void setTutors(List<Student> tutors) {
		this.tutors = tutors;
	}
}

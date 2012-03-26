package no.niths.domain;

import no.niths.common.AppConstants;
import no.niths.domain.adapter.JsonCalendarAdapter;
import no.niths.domain.adapter.XmlCalendarAdapter;
import no.niths.domain.location.Room;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@XmlRootElement
@Entity
@Table(name = AppConstants.EXAMS)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Exam implements Serializable {

    @Transient
    private static final long serialVersionUID = 3498003779752196516L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Size(min = 3, max = 80, message ="The length of the name must be between 3 to 80 letters")
    private String name;

    @Column(name="exam_type")
    @Size(min = 2, max = 30, message ="The length of the exam_type must be between 2 to 10 letters")
    private String examType;

    @Column(name="start_time")
    @Temporal(TemporalType.TIMESTAMP)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(XmlCalendarAdapter.class)
    private Calendar startTime;

    @Column(name="end_time")
    @Temporal(TemporalType.TIMESTAMP)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(XmlCalendarAdapter.class)
    private Calendar endTime;

    @Column(name="allowed_aid")
    private String allowedAid;

    @JsonIgnore
    @XmlTransient
    @ManyToOne(fetch = FetchType.EAGER, targetEntity= Subject.class)
    @JoinTable(name = "exam_subjects",
            joinColumns = @JoinColumn(name = "exams_id"),
            inverseJoinColumns = @JoinColumn(name = "subjects_id"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Subject subject;

    @JsonIgnore
    @XmlTransient
    @ManyToMany(fetch = FetchType.LAZY, targetEntity= Room.class)
    @JoinTable(name = "rooms_exams",
            joinColumns = @JoinColumn(name = "exams_id"),
            inverseJoinColumns = @JoinColumn(name = "rooms_id"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Room> rooms = new ArrayList<Room>();

    public Exam(){
    	this(null,null,null,null,null);
    	setRooms(null);
    	setSubject(null);
    }

    public Exam(String name, String examType, String allowedAid, GregorianCalendar startTime, GregorianCalendar endTime){
        setName(name);
        setExamType(examType);
        setAllowedAid(allowedAid);
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

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getAllowedAid() {
        return allowedAid;
    }

    public void setAllowedAid(String allowedAid) {
        this.allowedAid = allowedAid;
    }

    @JsonSerialize(using = JsonCalendarAdapter.class)
    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    @JsonSerialize(using = JsonCalendarAdapter.class)
    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public boolean equals(Object that) {
        if(!(that instanceof Exam)) return false;
        Exam exam = (Exam) that;

        return exam == this ? true : exam.getId() == id
                ? true : false;
    }

    @JsonIgnore
    public boolean isEmpty(){
        return (id == null && name == null && examType == null && allowedAid ==  null
                && startTime == null && endTime == null);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s][%s]", id, name, examType);
    }
}

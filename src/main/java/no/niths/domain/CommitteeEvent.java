package no.niths.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import no.niths.common.AppConstants;
import no.niths.common.JsonCalendarAdapter;
import no.niths.common.XmlCalendarAdapter;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@Entity
@Table(name = AppConstants.COMMITTEE_EVENTS)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@XmlAccessorType(XmlAccessType.FIELD) 
public class CommitteeEvent implements Serializable{

    @Transient
    private static final long serialVersionUID = 1878727682733503699L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(min = 3, max = 30, message ="The length of the name must be between 3 to 30 letters")
    private String name;

    @Column(length=500)
    @Size(max = 500, message ="The length of the description must not exceed 500 letters")
    private String description;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @XmlSchemaType(name = "date")  
    @XmlJavaTypeAdapter(XmlCalendarAdapter.class)  
    private Calendar startTime;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @XmlSchemaType(name = "date")  
    @XmlJavaTypeAdapter(XmlCalendarAdapter.class)  
    private Calendar endTime;
    
    @ManyToOne
    private Committee committee;

    public CommitteeEvent() {
        this(null, null, null, null,null);
    }

    public CommitteeEvent(Long id, String name, String description,
            GregorianCalendar startTime,GregorianCalendar endTime) {
        setId(id);
        setName(name);
        setDescription(description);
        setEndTime(endTime);
        setStartTime(startTime);
    }

  

    public CommitteeEvent(String name, String description,
    		 GregorianCalendar startTime,GregorianCalendar endTime) {
    	setName(name);
        setDescription(description);
        setEndTime(endTime);
        setStartTime(startTime);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    @JsonIgnore
    public Committee getCommittee() {
        return committee;
    }
    
    
    public void setCommittee(Committee committee) {
        this.committee = committee;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CommitteeEvent)) {
            return false;
        }
        CommitteeEvent s = (CommitteeEvent) obj;
        return s == this ? true : s.getId() == id ? true : false;
    }
    
    @JsonIgnore
	public boolean isEmpty() {
		return description == null && name == null && id == null && endTime==null && startTime == null ;
	}

    @JsonSerialize(using=JsonCalendarAdapter.class)
	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	@JsonSerialize(using=JsonCalendarAdapter.class)
	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}
}
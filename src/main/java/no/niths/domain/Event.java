package no.niths.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import no.niths.common.AppConstants;
import no.niths.domain.adapter.JsonCalendarAdapter;
import no.niths.domain.adapter.XmlCalendarAdapter;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@XmlRootElement(name = AppConstants.EVENT)
@Entity
@Table(name = AppConstants.EVENTS)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@XmlAccessorType(XmlAccessType.FIELD)
public class Event implements Serializable {

	@Transient
	private static final long serialVersionUID = 1878727682733503699L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable=false)
	@Size(min = 3, max = 30, message = "The length of the name must be between 3 to 30 letters")
	private String name;

	@Column(length = 500)
	@Size(max = 500, message = "The length of the description must not exceed 500 letters")
	private String description;

	@Column(name = "startTime")
	@Temporal(TemporalType.TIMESTAMP)
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(XmlCalendarAdapter.class)
	private Calendar startTime;

	@Column(name = "endTime")
	@Temporal(TemporalType.TIMESTAMP)
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(XmlCalendarAdapter.class)
	private Calendar endTime;

	@Column(name = "latitude")
	private Double latitude;

	@Column(name = "longitude")
	private Double longitude;

	@Column(name = "tags")
	private String tags;

	@JsonIgnore
	@XmlTransient
	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Committee.class)
	@Cascade(CascadeType.ALL)
	@JoinTable(name = "committees_events", 
			joinColumns = @JoinColumn(name = "events_id"), 
			inverseJoinColumns = @JoinColumn(name = "committees_id"))
	private List<Committee> committees = new ArrayList<Committee>();

	public Event() {
		this(null, null, null, null, null);
	}

	public Event(Long id, String name, String description,
			GregorianCalendar startTime, GregorianCalendar endTime) {
		setId(id);
		setName(name);
		setDescription(description);
		setEndTime(endTime);
		setStartTime(startTime);
	}

	public Event(String name, String description, GregorianCalendar startTime,
			GregorianCalendar endTime) {
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

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Event)) {
			return false;
		}
		Event s = (Event) obj;
		return s == this ? true : s.getId() == id ? true : false;
	}

	@JsonIgnore
	public boolean isEmpty() {
		return description == null && name == null && id == null
				&& endTime == null && startTime == null;
	}
	
	@Override
	public String toString() {
		return String.format("[%s][%s][%s]", id, name, description);
	}

	@JsonSerialize(using = JsonCalendarAdapter.class)
	public Calendar getEndTime() {
		return endTime;
	}

	public void setEndTime(Calendar endTime) {
		this.endTime = endTime;
	}

	@JsonSerialize(using = JsonCalendarAdapter.class)
	public Calendar getStartTime() {
		return startTime;
	}

	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public List<Committee> getCommittees() {
		return committees;
	}

	public void setCommittees(List<Committee> committees) {
		this.committees = committees;
	}

}
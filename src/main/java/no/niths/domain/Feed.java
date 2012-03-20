package no.niths.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;
import no.niths.domain.location.Location;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@XmlRootElement(name = AppConstants.FEED)
@Entity
@Table(name = AppConstants.FEEDS)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Feed implements Serializable {

	@Transient
	private static final long serialVersionUID = -7280194759501252804L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String message;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "feeds_location", 
		joinColumns = @JoinColumn(name = "feeds_id"), 
		inverseJoinColumns = @JoinColumn(name = "location_id"))
	@Cascade(CascadeType.ALL)
	private Location location;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "feeds_student", 
		joinColumns = @JoinColumn(name = "feeds_id"), 
		inverseJoinColumns = @JoinColumn(name = "student_id"))
	@Cascade(CascadeType.ALL)
	private Student student;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {

		return String.format("[%s][%s][]", "");
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}

package no.niths.domain.location;

import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import no.niths.common.AppConstants;
import no.niths.domain.Domain;
import no.niths.domain.Exam;
import no.niths.domain.Subject;
import no.niths.domain.signaling.AccessField;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = AppConstants.ROOMS)
@XmlRootElement
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Room implements Domain {

	@Transient
	private static final long serialVersionUID = -664567726655902624L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "room_name", unique = true)
	private String roomName;

	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	@JoinTable(name = "rooms_accessfields", joinColumns = @JoinColumn(name = "room_id"), inverseJoinColumns = @JoinColumn(name = "accessfield_id"))
	private List<AccessField> accessFields = new ArrayList<AccessField>();

	@ManyToMany(fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	@JoinTable(name = "rooms_exams", joinColumns = @JoinColumn(name = "room_id"), inverseJoinColumns = @JoinColumn(name = "exams_id"))
	private List<Exam> exams = new ArrayList<Exam>();

	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "subjects_room", joinColumns = @JoinColumn(name = "room_id"), inverseJoinColumns = @JoinColumn(name = "subjects_id"))
	@Cascade(CascadeType.ALL)
	private List<Subject> subjects = new ArrayList<Subject>();

	public Room(String roomName) {
		setRoomName(roomName);
	}

	public Room() {
		this(null);
		setSubjects(null);
		setExams(null);
		setAccessFields(null);

	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setAccessFields(List<AccessField> accessFields) {
		this.accessFields = accessFields;
	}

	public List<AccessField> getAccessFields() {
		return accessFields;
	}

	public void setExams(List<Exam> exams) {
		this.exams = exams;
	}

	@XmlTransient
	@JsonIgnore
	public List<Exam> getExams() {
		return exams;
	}

	@Override
	public String toString() {
		return String.format("[%s][%s]", id, roomName);
	}

	@XmlTransient
	@JsonIgnore
	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Room))
			return false;

		Room room = (Room) obj;
		return room.getId() == getId();
	}
}
package no.niths.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;


@XmlRootElement
@Entity
@Table(name ="mentors")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Mentor implements Serializable {

	@Transient
	private static final long serialVersionUID = -3434555328809873472L;
	
	@Id
	@Column(name="identifier")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Column(nullable=false,name="group_id")
	private int groupId;
	
	@OneToMany(targetEntity=Student.class)
	List<Student> mentors = new ArrayList<Student>();
	
	public Mentor() {
		this(0);
	}
	
	public Mentor(int grupeId) {
		setGroupId(grupeId);
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

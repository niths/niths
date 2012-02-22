package no.niths.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;

import org.codehaus.jackson.map.annotate.JsonSerialize;


@XmlRootElement
@Entity
@Table(name =AppConstants.MENTORS)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Mentor implements Serializable {

	@Transient
	private static final long serialVersionUID = -3434555328809873472L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@Column(name="group_id")
	private int groupId;
	
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

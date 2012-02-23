package no.niths.domain.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import no.niths.common.AppConstants;

@XmlRootElement
@Entity
@Table(name = AppConstants.ROLES)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Role implements Serializable {
	
	@Transient
	private static final long serialVersionUID = 3089189542515256814L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public boolean equals( final Object obj ){
		if( this == obj )
			return true;
		if( obj == null )
			return false;
		if( this.getClass() != obj.getClass() )
			return false;
		final Role other = (Role) obj;
		if(role.equals(other.role)){
			return true;
		}
		return false;
	}

}

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Email;

@XmlRootElement
@Entity
@Table(name = AppConstants.DEVELOPERS)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Developer implements Serializable{

	@Transient
	private static final long serialVersionUID = -85961208307674962L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column
    @Email
    private String email;
    
    @Column
    @Size(min = 3, max = 30, message = "Length min = 3, max = 30")
    private String name;
    
    @OneToMany(mappedBy = "developer")
    @Cascade(CascadeType.ALL)
    List<Application> apps = new ArrayList<Application>();

    
	public List<Application> getApps() {
		return apps;
	}

	public void setApps(List<Application> apps) {
		this.apps = apps;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return String.format("[%s][%s][%s]", id, email, name);
	}
    
    @Override
    public boolean equals(Object that) {
        if (!(that instanceof Developer))	
            return false;
        Developer s = (Developer) that;
        return s == this ? true : s.getId() == id ? true : false;
    }
    
}

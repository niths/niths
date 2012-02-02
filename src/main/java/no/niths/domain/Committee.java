package no.niths.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@XmlRootElement
@Entity
@Table(name = AppConstants.COMMITTEES)
public class Committee implements Serializable {

    @Transient
    private static final long serialVersionUID = 2901268519809419196L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @Size(min = 3, max = 30, message ="The length of the name must be between 3 to 30 letters")
    private String name;

    @Column(length=500)
    @Size(max = 500, message ="The length of the description must not exceed 500 letters")
    private String description;

//    @ManyToMany(fetch = FetchType.LAZY)
//    private List<Student> members = new ArrayList<Student>();

    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private List<CommitteeEvent> events = new ArrayList<CommitteeEvent>();
    
    public Committee() {
        this("", "");
    }

    public Committee(String name, String description) {
        this(-1, name, description);
    }

    public Committee(long id, String name, String description) {
        setId(id);
        setName(name);
        setDescription(description);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<CommitteeEvent> getEvents() {
        return events;
    }

    public void setEvents(List<CommitteeEvent> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object that) {
        if (!(that instanceof Committee))	
            return false;
        Committee s = (Committee) that;
        return s == this ? true : s.getId() == id ? true : false;
    }
    
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
		return result;
	}

    @Override
    public String toString() {
        return name;
    }

//    @JsonIgnore
//    public List<Student> getStudents() {
//        return members;
//    }
//
//    public void setStudents(List<Student> students) {
//        this.members = students;
//    }

}
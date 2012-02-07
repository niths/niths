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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@XmlRootElement
@Entity
@Table(name = AppConstants.COMMITTEES)
public class Committee implements Serializable {

	@Transient
	private static final long serialVersionUID = 7191935818417886723L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(min = 3, max = 30, message ="The length of the name must be between 3 to 30 letters")
    private String name;

    @Column(length=500)
    @Size(max = 500, message ="The length of the description must not exceed 500 letters")
    private String description;

//    @ManyToMany(fetch = FetchType.LAZY)
//    private List<Student> members = new ArrayList<Student>();

    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private List<CommitteeEvent> events = new ArrayList<CommitteeEvent>();
    
    public Committee() {
        this(null, null);
    }

    public Committee(String name, String description) {
        this(null, name, description);
    }

    public Committee(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public List<CommitteeEvent> getEvents() {
        return events;
    }

    public void setEvents(List<CommitteeEvent> events) {
        this.events = events;
    }

    @JsonIgnore
    public boolean isEmpty() {
        return (id == null && name == null && description == null);
    }

    @Override
    public boolean equals(Object that) {
        if (!(that instanceof Committee))	
            return false;
        Committee s = (Committee) that;
        return s == this ? true : s.getId() == id ? true : false;
    }

    @Override
    public String toString() {
        return name + " " + description;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
        return result;
    }
   
    
//    public List<Student> getStudents() {
//        return members;
//    }
//
//    public void setStudents(List<Student> students) {
//        this.members = students;
//    }

}
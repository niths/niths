package no.niths.domain.school;

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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppNames;
import no.niths.domain.Domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@XmlRootElement
@Entity
@Table(name = AppNames.COMMITTEES)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Committee implements Domain {

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name="committee_leaders", 
    		uniqueConstraints={@UniqueConstraint(
    				columnNames ={"committees_id", "leaders_id"})} )
    private List<Student> leaders = new ArrayList<Student>();

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Event.class)
    @Cascade(CascadeType.ALL)
    @JoinTable(name = "committees_events", 
    		uniqueConstraints = {@UniqueConstraint(columnNames={"committees_id","events_id"})})
    private List<Event> events = new ArrayList<Event>();
    
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Student.class)
    @Cascade(CascadeType.ALL)
	@JoinTable(name = "students_committees", 
		joinColumns = @JoinColumn(name = "committees_id"), 
		inverseJoinColumns = @JoinColumn(name = "students_id"))
    private List<Student> members = new ArrayList<Student>();
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name               = "feeds_committee",
            joinColumns        = @JoinColumn(name = "committee_id"),
            inverseJoinColumns = @JoinColumn(name = "feeds_id"))
    @Cascade(CascadeType.ALL)
    private List<Feed> feeds = new ArrayList<Feed>();
    
    public Committee() {
        this(null, null,null);
        setEvents(null);
        setMembers(null);
        setLeaders(null);
    }

    public Committee(String name, String description) {
        this(null, name, description);
    }

    public Committee(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Committee(Long committeeId) {
		setId(committeeId);
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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
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
    	return String.format("[%s][%s][%s]", id, name, description);
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( name == null ) ? 0 : name.hashCode() );
        return result;
    }
   
    
    public List<Student> getLeaders() {
        return leaders;
    }

    public void setLeaders(List<Student> students) {
        this.leaders = students;
    }

	public List<Student> getMembers() {
		return members;
	}

	public void setMembers(List<Student> members) {
		this.members = members;
	}

	public List<Feed> getFeeds() {
		return feeds;
	}

	public void setFeeds(List<Feed> feeds) {
		this.feeds = feeds;
	}
}

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
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name = AppConstants.COURSES)
@XmlRootElement
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Course implements Serializable {

    @Transient
    private static final Long serialVersionUID = 363916933860451377L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(min = 3, max = 30, message ="The length of the name must be between 3 to 30 letters")
    private String name;

    @Column(length=500)
    @Size(max = 500, message ="The length of the description must not exceed 500 letters")
    private String description;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.SAVE_UPDATE)
	private List<Topic> topics = new ArrayList<Topic>();

    public Course() {
        this(null, null, null);
    }

    public Course(String name, String description) {
        this(null, name, description);
    }

    public Course(Long id, String name, String description) {
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

    public List<Topic> getTopics() {
		return topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
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

    @JsonIgnore
    public boolean isEmpty() {
        return (id == null && name == null && description == null);
    }

    @Override
    public boolean equals(Object that) {
    	if(!(that instanceof Course)) return false;
        Course course = (Course) that;

        return course == this ? true : course.getId() == id
                ? true : false;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s", id, name, description);
    }
}
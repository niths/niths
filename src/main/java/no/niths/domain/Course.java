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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(
		name = AppConstants.COURSES, 
		uniqueConstraints={@UniqueConstraint(columnNames ={"name", "grade"})})
@XmlRootElement
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Course implements Serializable {

    @Transient
    private static final Long serialVersionUID = 363916933860451377L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    @Size(min = 3, max = 30, message ="The length of the name must be between 3 to 30 letters")
    private String name;
    
    @Column
	@Max(value = 3, message = "Can not be larger then 3")
	@Min(value = 1, message = "Can not be smaller then 1")
	private Integer grade;
    
    @Column
    @Pattern(regexp = "(^$)|((f|F)all|(s|S)pring)", message = "Must be Spring or Fall")
    private String term;

    @Column(length=500)
    @Size(max = 500, message ="The length of the description must not exceed 500 letters")
    private String description;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.SAVE_UPDATE)
	private List<Subject> subjects = new ArrayList<Subject>();

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

    public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> topics) {
		this.subjects = topics;
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

//    @JsonIgnore
//    public boolean isEmpty() {
//        return (id == null && name == null && description == null);
//    }

    public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
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
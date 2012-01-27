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

@XmlRootElement
@Entity
@Table(name = "courses")
public class Course implements Serializable {

    @Transient
    private static final long serialVersionUID = 363916933860451377L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;

    @Column
    private String description;

    public Course() {
        this(-1, "", "");
    }

    public Course(String name, String description) {
        this(-1, name, description);
    }

    public Course(long id, String name, String description) {
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
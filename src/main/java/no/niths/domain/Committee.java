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

import no.niths.common.AppConstants;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = AppConstants.COMMITTEES)
public class Committee implements Serializable {

    @Transient
    private static final long serialVersionUID = 2901268519809419196L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Student> members = new ArrayList<Student>();

    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade(CascadeType.SAVE_UPDATE)
    private List<CommitteeEvents> events = new ArrayList<CommitteeEvents>();

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

    public List<CommitteeEvents> getEvents() {
        return events;
    }

    public void setEvents(List<CommitteeEvents> events) {
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
    public String toString() {
        return "Committee";
    }

    public List<Student> getStudents() {
        return members;
    }

    public void setStudents(List<Student> students) {
        this.members = students;
    }
}
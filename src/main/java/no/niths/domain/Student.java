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
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@XmlRootElement
@Entity 
@Table(name = AppConstants.STUDENTS)
public class Student implements Serializable {

    @Transient
    private static final long serialVersionUID = 8441269238845961513L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
//  @NotNull
//  @Size(min = 1, max = 35, message = "Must be minimun 1 char and max 35 chars")
    private String firstName;
    
    @Column(name = "last_name")
//  @NotNull
//  @Size(min = 1, max = 35, message = "Must be minimun 1 char and max 35 chars")
    private String lastName;
    
    @Column
//  @Email(message = "Not a valid email")
    private String email;
    
    @Column(name = "phone_number")
//  @Pattern(regexp = "(^$)|([0-9]{8})", message = "Not a valid number")
    private String telephoneNumber;

    @Column
//  @Size(min = 0, max = 255, message = "Can not be more then 255 chars")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @Cascade(CascadeType.SAVE_UPDATE)
    private List<Course> courses;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "members")
    @Cascade(CascadeType.SAVE_UPDATE)
    private List<Committee> committees;

    public Student() {
        this("", "", "");
    }

    public Student(String firstName, String lastName){
        this(firstName, lastName, "");
    }
    
     public Student(String firstName, String lastName, String email){
         this(firstName, lastName, email, "", "");
     }

    public Student(String firstName, String lastName, String email, String telephoneNumber, String description) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setTelephoneNumber(telephoneNumber);
        setDescription(description);
        setCourses(new ArrayList<Course>());
        setCommittees(new ArrayList<Committee>());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Student)) {
            return false;
        }
        Student s = (Student) obj;
        return s == this ? true : s.getId() == id ? true : false;
    }

    public List<Committee> getCommittees() {
        return committees;
    }

    public void setCommittees(List<Committee> committees) {
        this.committees = committees;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
package no.niths.domain.school;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import no.niths.common.constants.DomainConstantNames;
import no.niths.common.constants.ValidationConstants;
import no.niths.domain.Domain;
import no.niths.domain.adapter.JsonCalendarDeserializerAdapter;
import no.niths.domain.adapter.JsonCalendarSerializerAdapter;
import no.niths.domain.adapter.XmlCalendarAdapter;
import no.niths.domain.location.Location;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Domain class for Feed
 *
 * <p>
 * Feed has these variables:
 * message = example Husk pub etter eksamen,
 * published = example 21/03/2012-13:00
 * </p>
 * <p>
 * And relations too:
 * Student,
 * Location,
 * Committee
 * </p>
 * <p>
 * An application utilizing this class could
 * be a "home work helper" app.
 * Students can post questions which others could read
 * and respond to like in Twitter.
 * </p>
 */
@XmlRootElement
@Entity
@Table(name = DomainConstantNames.FEEDS)
@XmlAccessorType(XmlAccessType.FIELD)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Feed implements Domain {

    @Transient
    private static final long serialVersionUID = -7280194759501252804L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Pattern(
            regexp  = ValidationConstants.LARGE,
            message = "Invalid message (should be 2 -500 alphanumeric letters")
    private String message;

    @Column(name = "published")
    @Temporal(TemporalType.TIMESTAMP)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(XmlCalendarAdapter.class)
    private Calendar published;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "feeds_location", joinColumns = @JoinColumn(name = "feeds_id"), inverseJoinColumns = @JoinColumn(name = "location_id"))
    @Cascade(CascadeType.ALL)
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "feeds_student", joinColumns = @JoinColumn(name = "feeds_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    @Cascade(CascadeType.ALL)
    private Student student;

    @JsonSerialize(as=Committee.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "feeds_committee", joinColumns = @JoinColumn(name = "feeds_id"), inverseJoinColumns = @JoinColumn(name = "committee_id"))
    @Cascade(CascadeType.ALL)
    private Committee committee;

    public Feed() {
        this(null, null);
        setPublished(null);
        setStudent(null);
        setLocation(null);
        setCommittee(null);
    }

    public Feed(String message) {
        setMessage(message);
        setPublished(new GregorianCalendar());
    }

    public Feed(Long feedId) {
        setId(feedId);
    }

    public Feed(Long feedId, String message) {
        setId(feedId);
        setMessage(message);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonSerialize(as = Student.class)
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that)
            return true;

        if (!(that instanceof Feed))
            return false;

        Feed feed = (Feed) that;

        return (getId() == (feed.getId()));
    }

    @Override
    public String toString() {
        return String.format("[%s][%s]", id, message);
    }

    @JsonSerialize(as = Location.class)
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonSerialize(using = JsonCalendarSerializerAdapter.class)
    public Calendar getPublished() {
        return published;
    }

    @JsonDeserialize(using = JsonCalendarDeserializerAdapter.class)
    public void setPublished(Calendar published) {
        this.published = published;
    }

    public Committee getCommittee() {
        return committee;
    }

    public void setCommittee(Committee committee) {
        this.committee = committee;
    }
}

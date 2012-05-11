package no.niths.domain;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import no.niths.common.constants.AdminConstantNames;
import no.niths.common.constants.ValidationConstants;
import no.niths.domain.adapter.JsonCalendarDeserializerAdapter;
import no.niths.domain.adapter.JsonCalendarSerializerAdapter;
import no.niths.domain.adapter.XmlCalendarAdapter;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
/**
 * Domain class for APIEvents
 * To persist the event,  annotate any public method with @ApiEvent(title = "title).
 * 
 * The method must be public and take one parameter.
 * 
 * Title = the title from the annotation 
 * Description = the objects toString()
 * 
 * How to use:
 * @ApiEvent(title="Something happened")
 * public void anyMethod(Object obj){}
 * 
 *
 */
@XmlRootElement(name = "apievent")
@Entity
@Table(name = AdminConstantNames.API_EVENTS)
@XmlAccessorType(XmlAccessType.FIELD)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class APIEvent implements Domain{

    private static final String EVENTTIME = "eventtime";

    @Transient
    private static final long serialVersionUID = -5311081154825173386L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OrderBy
    private Long id;

    @Column
    @Pattern(
            regexp  = ValidationConstants.REGULAR,
            message = "Invalid title (should be 2 - 80 alphanumeric letters)")
    private String title;

    @Column(name= "eventTime")
    @Temporal(TemporalType.TIMESTAMP)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(XmlCalendarAdapter.class)
    @XmlElement(name=EVENTTIME)
    private Calendar eventTime;

    @Column
    @Pattern(
            regexp  = ValidationConstants.LARGE,
            message = "Invalid title (should be 2 - 500 alphanumeric letters)")
    private String description;

    public APIEvent() {
        this(null,null,null);
        this.id = null;
    }

    public APIEvent(
            String title, String description, GregorianCalendar eventTime) {
        this.title       = title;
        this.description = description;
        this.eventTime  = eventTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format(
                "[%s][%s][%s][%s]", id, eventTime, title, description);
    }

    @JsonSerialize(using = JsonCalendarSerializerAdapter.class)
    public Calendar getEventTime() {
        return eventTime;
    }

    @JsonDeserialize(using=JsonCalendarDeserializerAdapter.class)
    public void setEventTime(Calendar eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    public boolean equals(Object that) {
        if (!(that instanceof APIEvent))    
            return false;
        APIEvent s = (APIEvent) that;
        return s == this ? true : s.getId() == id ? true : false;
    }
}
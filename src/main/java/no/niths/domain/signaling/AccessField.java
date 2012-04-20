package no.niths.domain.signaling;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import no.niths.application.rest.exception.BadRequestException;
import no.niths.common.AppNames;
import no.niths.domain.Domain;
import no.niths.domain.location.Room;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Entity
@Table(name = AppNames.ACCESS_FIELDS)
@XmlRootElement(name = "accessfield")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AccessField implements Domain {

    private static final long serialVersionUID = 2425345455743938142L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "min_range")
    private Integer minRange;

    @Column(name = "max_range")
    private Integer maxRange;

    @JsonIgnore
    @XmlTransient
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "rooms_accessfields",
        joinColumns =        @JoinColumn(name = "accessfield_id"),
        inverseJoinColumns = @JoinColumn(name = "room_id"))
    private List<Room> rooms = new ArrayList<Room>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "accessfields_accesspoints",
        joinColumns =        @JoinColumn(name = "accessfield_id"),
        inverseJoinColumns = @JoinColumn(name = "accesspoint_id"))
    private AccessPoint accessPoint;

    public AccessField() {
    	this(null, null);
    	setAccessPoint(null);
    	setRooms(null);
    }

    public AccessField(Integer min, Integer max) {
        this.maxRange = max;
        this.minRange = min;
        validateRanges();
    }

    public AccessField(Long id) {
        this.id = id;
    }

    private void validateRanges() {
        if (minRange != null && maxRange != null && minRange > maxRange) {
            throw new BadRequestException(
                    "Min range is greater than max range");
        }
    }

    public boolean isWithinRanges(AccessField accessField) {
        return
                accessField.getId() == id &&
                accessField.getMaxRange() <= maxRange &&
                accessField.getMinRange() >= minRange;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setMinRange(Integer minRange) {
        this.minRange = minRange;
        validateRanges();
    }

    @XmlElement(name = "minrange")
    public Integer getMinRange() {
        return minRange;
    }

    public void setMaxRange(Integer maxRange) {
        this.maxRange = maxRange;
        validateRanges();
    }

    @XmlElement(name = "maxrange")
    public Integer getMaxRange() {
        return maxRange;
    }

    @XmlElement(name = "accesspoint")
    @JsonSerialize(as=AccessPoint.class)
    public AccessPoint getAccessPoint() {
        return accessPoint;
    }

    public void setAccessPoint(AccessPoint accessPoint) {
        this.accessPoint = accessPoint;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if(obj == this) return true;
    	if(!(obj instanceof AccessField))return false;
    	
    	AccessField af = (AccessField)obj;
    	return getId() == af.getId();
    }
    
    @Override
    public String toString() {
    	return String.format("[%s][%s][%s]", id, minRange, maxRange);
    }
}
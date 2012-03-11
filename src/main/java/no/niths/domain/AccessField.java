package no.niths.domain;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = AppConstants.ACCESS_FIELDS)
@XmlRootElement
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AccessField implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinTable(name = "access_fields_power_maps")
    @ElementCollection(fetch = FetchType.EAGER)
    private Map<Integer, Integer> powerMap;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private AccessPoint accessPoint;

    public void setPowerMap(Map<Integer, Integer> powerMap) {
        this.powerMap = powerMap;
    }

    public Map<Integer, Integer> getPowerMap() {
        return powerMap;
    }

    public void setAccessPoint(AccessPoint accessPoint) {
        this.accessPoint = accessPoint;
    }

    public AccessPoint getAccessPoint() {
        return accessPoint;
    }
}
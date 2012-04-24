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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import no.niths.common.constants.DomainConstantNames;
import no.niths.domain.Domain;

import org.codehaus.jackson.annotate.JsonIgnore;

@XmlRootElement(name = "accesspoint")
@Entity
@Table(name = DomainConstantNames.ACCESS_POINTS)
public class AccessPoint implements Domain {

    @Transient
    private static final long serialVersionUID = 8118983219932188402L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String address;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "accessfields_accesspoints",
    joinColumns = @JoinColumn(name = "accesspoint_id"), 
    inverseJoinColumns = @JoinColumn(name = "accessfield_id"))
    private List<AccessField> accessFields = new ArrayList<AccessField>();

    public AccessPoint() {
        this(null);
        setAccessFields(null);
    }

    public AccessPoint(String address) {
        this.address = address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @JsonIgnore
    @XmlTransient
    public List<AccessField> getAccessFields() {
        return accessFields;
    }

    public void setAccessFields(List<AccessField> accessFields) {
        this.accessFields = accessFields;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof AccessPoint))
            return false;
        AccessPoint ap = (AccessPoint) obj;
        return (ap.getId() == getId());
    }
}
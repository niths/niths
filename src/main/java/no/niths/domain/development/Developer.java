package no.niths.domain.development;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import no.niths.common.constants.DomainConstantNames;
import no.niths.common.constants.ValidationConstants;
import no.niths.domain.Domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.validator.constraints.Email;
import org.jasypt.hibernate4.type.EncryptedStringType;
/**
 * Domain class for Developer
 *
 * <p>
 * Developer has these variables:
 * email = example developer@nith.no (can not be null),
 * name = example Ola Normann (can not be null),
 * enabled = example true,
 * developerToken = example,
 * developerKey = example
 * </p>
 * <p>
 * And relations too:
 * Application
 * </p>
 * <p>
 * For access to the API, the developer must be enabled
 * and have key+token provided in the request header
 * together with the application key+token
 * </p>
 */
@TypeDef(name = "encryptedString", typeClass = EncryptedStringType.class, parameters = { @Parameter(name = "encryptorRegisteredName", value = "strongHibernateStringEncryptor") })
@XmlRootElement
@Entity
@Table(name = DomainConstantNames.DEVELOPERS)
@XmlAccessorType(XmlAccessType.FIELD)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Developer implements Domain {

    @Transient
    private static final long serialVersionUID = -85961208307674962L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Email
    @NotNull
    private String email;

    @Column(unique = true)
    @NotNull
    @Pattern(
            regexp  = ValidationConstants.REGULAR,
            message = "Invalid name (should be 2 - 50 alphanumeric characters")
    private String name;

    @JsonIgnore
    @XmlTransient
    @Column
    private Boolean enabled;

    @JsonIgnore
    @XmlTransient
    @Column(name = "developer_token")
    @Type(type = "encryptedString")
    private String developerToken;

    @JsonIgnore
    @XmlTransient
    @Column(name = "developer_key")
    private String developerKey;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Application.class)
    @JoinTable(
            name               = "developers_applications",
            joinColumns        = @JoinColumn(name = "developers_id"),
            inverseJoinColumns = @JoinColumn(name = "applications_id"))
    @Cascade(CascadeType.ALL)
    private List<Application> apps = new ArrayList<Application>();

    public Developer() {
        this.id             = null;
        this.name           = null;
        this.email          = null;
        this.enabled        = null;
        this.developerToken = null;
        this.apps           = null;
    }

    public Developer(String name) {
        this.name = name;
    }

    public Developer(String name, String email) {
        this.name  = name;
        this.email = email;
    }

    public List<Application> getApps() {
        return apps;
    }

    public void setApps(List<Application> apps) {
        this.apps = apps;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s][%s]", id, email, name);
    }

    @Override
    public boolean equals(Object that) {
        if (!(that instanceof Developer))
            return false;
        Developer s = (Developer) that;
        return s == this ? true : s.getId() == id ? true : false;
    }

    public String getDeveloperToken() {
        return developerToken;
    }

    public void setDeveloperToken(String developerToken) {
        this.developerToken = developerToken;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getDeveloperKey() {
        return developerKey;
    }

    public void setDeveloperKey(String developerKey) {
        this.developerKey = developerKey;
    }

}

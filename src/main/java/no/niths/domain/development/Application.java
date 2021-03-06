package no.niths.domain.development;

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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
import org.jasypt.hibernate4.type.EncryptedStringType;
/**
 * Domain class for Application
 *
 * <p>
 * Application has these variables:
 * title = example Appen (can not be null),
 * description = example ,
 * iconUrl = example ,
 * applicationToken = example ,
 * applicationKey = example ,
 * enabled = example true,
 * requests = example
 * </p>
 * <p>
 * And relations too:
 * Developer
 * </p>
 * <p>
 * For access to the API, the app must be enabled
 * and have key+token provided in the request header.
 * </p>
 */
@TypeDef(name = "encryptedString" ,
typeClass = EncryptedStringType.class,
parameters = {
    @Parameter(name = "encryptorRegisteredName", value = "strongHibernateStringEncryptor")
})
@XmlRootElement
@Entity
@Table(name = DomainConstantNames.APPLICATIONS)
@XmlAccessorType(XmlAccessType.FIELD)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Application implements Domain {

    @Transient
    private static final long serialVersionUID = 2489514983742481691L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull
    @Pattern(
            regexp  = ValidationConstants.REGULAR,
            message = "Invalid title (should be 2 - 50 alphanumeric letters)")
    private String title;

    @Column(length = 500)
    @Size(max = 500, message = "The length of the description must not exceed 500 letters")
    @Pattern(
            regexp = "((\\w[\\w\\s,-_+!.]\\w){1,250})?")
    private String description;

    @Column(name = "icon_url")
    @Size(max = 200, message = "Url to long, max length = 200")
    @XmlElement(name="iconurl")
    private String iconUrl;

    @JsonIgnore
    @XmlTransient
    @Column(name = "application_token")
    @Type(type="encryptedString")
    private String applicationToken;

    @JsonIgnore
    @XmlTransient
    @Column(name = "application_key")
    private String applicationKey;
    
    @JsonIgnore
    @XmlTransient
    @Column
    private Boolean enabled;
    
    // Counter that keeps track of number of requests
    // towards the API
    @Column
    private Long requests;

    @JsonSerialize(as=Developer.class)
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Developer.class)
    @JoinTable(
            name = "developers_applications",
            joinColumns = @JoinColumn(name = "applications_id"),
            inverseJoinColumns = @JoinColumn(name = "developers_id"))
    @Cascade(CascadeType.ALL)
    private Developer developer;

    public Application() {
        this(null, null, null, null);
        setDeveloper(null);
    }
    
    public Application(String title){
        this.title = title;
    }

    public Application(String title, String description, String iconUrl,
            Developer developer) {
        setTitle(title);
        setDescription(description);
        setIconUrl(iconUrl);
        setDeveloper(developer);
    }

    public Application(Long applicationId) {
        setId(applicationId);
    }

    @JsonIgnore
    @XmlTransient
    public String getApplicationToken() {
        return applicationToken;
    }

    public void setApplicationToken(String token) {
        this.applicationToken = token;
    }

    @JsonIgnore
    @XmlTransient
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean isValid) {
        this.enabled = isValid;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s][%s][%s]", id, title, description,
                iconUrl);
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

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    @Override
    public boolean equals(Object that) {
        if (!(that instanceof Application))
            return false;
        Application s = (Application) that;
        return s == this ? true : s.getId() == id ? true : false;
    }

    public String getApplicationKey() {
        return applicationKey;
    }

    public void setApplicationKey(String applicationKey) {
        this.applicationKey = applicationKey;
    }

    public Long getRequests() {
        return requests;
    }

    public void setRequests(Long requests) {
        this.requests = requests;
    }
}

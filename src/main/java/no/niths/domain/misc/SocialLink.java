package no.niths.domain.misc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.constants.DomainConstantNames;
import no.niths.domain.Domain;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement(name = "sociallink")
@Entity
@Table(name = DomainConstantNames.SOCIAL_LINKS)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@XmlAccessorType(XmlAccessType.FIELD)
public class SocialLink implements Domain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Pattern(
            regexp  = ".*",
            message = "Invalid address")
    private String address;

    @XmlElement(name = "socialcommunity")
    @Column(name = "social_community")
    @Pattern(
            regexp  = ".*",
            message = "Invalid social community")
    private String socialCommunity;

    public SocialLink() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSocialCommunity() {
        return socialCommunity;
    }

    public void setSocialCommunity(String socialCommunity) {
        this.socialCommunity = socialCommunity;
    }
}
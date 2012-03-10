package no.niths.domain;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import no.niths.common.AppConstants;

@XmlRootElement
@Entity
@Table(name = AppConstants.ACCESS_FIELDS)
public class AccessField implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private Map<Integer, Integer> powerMap;

    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private AccessPoint accessPoint;
}
package no.niths.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * 
 * Example domain class
 * <p>
 * Showcases how to extend the API with custom domains
 * </p>
 * <p>
 * All domains must implement the Domain interface,
 * and override toString and equals
 * </p>
 * <p>
 * For attribute validation, check the other domain classes
 * </p>
 *
 */
public class ExampleDomain implements Domain{

	private static final long serialVersionUID = -6235554113298449810L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String attribute;

	public ExampleDomain(){
		this(null);
	}
	
	public ExampleDomain(String attribute){
		this.attribute = attribute;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	
	@Override
	public String toString() {
		return String.format("[%s][%s]", id, attribute);
	}
	
    @Override
    public boolean equals(Object that) {
        if (!(that instanceof APIEvent))	
            return false;
        ExampleDomain d = (ExampleDomain) that;
        return d == this ? true : d.getId() == id ? true : false;
    }

}

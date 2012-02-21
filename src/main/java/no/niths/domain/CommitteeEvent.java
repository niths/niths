package no.niths.domain;

import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
@Entity
public class CommitteeEvent extends Event {

	
	@Transient
	private static final long serialVersionUID = 7274159288917039061L;
	
	@ManyToOne
	private Committee committee;
	
	public CommitteeEvent(){
		super(null,null,null,null);
	}
	
	public CommitteeEvent(String name, String description,
			GregorianCalendar startTime, GregorianCalendar endTime) {
		super(name,description,startTime,endTime);
	}

	public Committee getCommittee() {
		return committee;
	}

	public void setCommittee(Committee committee) {
		this.committee = committee;
	}
}

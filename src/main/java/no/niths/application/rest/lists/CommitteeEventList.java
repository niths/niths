package no.niths.application.rest.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;
import no.niths.domain.CommitteeEvent;

@XmlRootElement(name = AppConstants.EVENTS)
public class CommitteeEventList extends ListAdapter<CommitteeEvent>{
    private static final long serialVersionUID = 3045223036129251886L;
    
    @SuppressWarnings("unused")
	@XmlElement(name = "event")
    private List<CommitteeEvent> eventData;

    public void setEventData(List<CommitteeEvent> data) {
        this.eventData = data;
    }
}

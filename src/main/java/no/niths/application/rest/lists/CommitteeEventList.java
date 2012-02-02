package no.niths.application.rest.lists;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;
import no.niths.domain.CommitteeEvent;

@XmlRootElement(name = AppConstants.COMMITTEES)
public class CommitteeEventList extends ArrayList<CommitteeEvent>{
    private static final long serialVersionUID = 3045223036129251886L;
    @XmlElement(name = "committee")
    private List<CommitteeEvent> eventData;

    public List<CommitteeEvent> getEventData() {
        return eventData;
    }

    public void setEventData(List<CommitteeEvent> eventData) {
        this.eventData = eventData;
    }
}

package no.niths.application.rest.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;
import no.niths.domain.CommitteeEvent;

@XmlRootElement(name = AppConstants.COMMITTEES)
public class CommitteeEventList {

    @XmlElement(name = "committee")
    private List<CommitteeEvent> eventData;

    public void setCommitteeData(List<CommitteeEvent> eventData) {
        this.eventData = eventData;
    }
}

package no.niths.application.rest.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;
import no.niths.domain.Committee;

@XmlRootElement(name = AppConstants.COMMITTEES)
public class CommitteeList {

    @XmlElement(name = "committee")
    private List<Committee> committeeData;

    public void setCommitteeData(List<Committee> committeeData) {
        this.committeeData = committeeData;
    }
}

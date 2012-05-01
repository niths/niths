package no.niths.application.rest.lists.school;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.constants.DomainConstantNames;
import no.niths.domain.school.Committee;

@XmlRootElement(name = DomainConstantNames.COMMITTEES)
public class CommitteeList extends ListAdapter<Committee> {

    private static final long serialVersionUID = -7988084285022242469L;

    @SuppressWarnings("unused")
    @XmlElement(name = "committee")
    private List<Committee> committeeData;

    @Override
    public void setData(List<Committee> committeeData) {
        this.committeeData = committeeData;
    }
}
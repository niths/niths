package no.niths.application.rest.lists.school;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.application.rest.lists.ListAdapter;
import no.niths.domain.school.FadderGroup;
/**
 * Class to contain a list of fadderGroup
 */
@XmlRootElement(name = "faddergroups")
public class FadderGroupList extends ListAdapter<FadderGroup> {

    private static final long serialVersionUID = 7386282831236634626L;

    @SuppressWarnings("unused")
    @XmlElement(name = "faddergroup")
    private List<FadderGroup> data;

    @Override
    public void setData(List<FadderGroup> data) {
        this.data = data;
    }
}
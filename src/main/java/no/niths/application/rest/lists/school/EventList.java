package no.niths.application.rest.lists.school;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.constants.DomainConstantNames;
import no.niths.domain.school.Event;
/**
 * Class to contain a list of events
 */
@XmlRootElement(name = DomainConstantNames.EVENTS)
public class EventList extends ListAdapter<Event> {
    private static final long serialVersionUID = 3045223036129251886L;

    @SuppressWarnings("unused")
    @XmlElement(name = "event")
    private List<Event> data;

    @Override
    public void setData(List<Event> data) {
        this.data = data;
    }
}
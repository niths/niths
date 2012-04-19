package no.niths.application.rest.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppNames;
import no.niths.domain.school.Event;

@XmlRootElement(name = AppNames.EVENTS)
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
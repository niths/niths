package no.niths.application.rest.lists.location;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.constants.AppNames;
import no.niths.domain.location.Location;

@XmlRootElement(name = AppNames.LOCATIONS)
public class LocationList extends ListAdapter<Location> {

    private static final long serialVersionUID = 7284616637714631167L;

    @SuppressWarnings("unused")
    @XmlElement(name = "location")
    private List<Location> data;

    @Override
    public void setData(List<Location> locationData) {
        this.data = locationData;
    }
}
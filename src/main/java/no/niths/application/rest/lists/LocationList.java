package no.niths.application.rest.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppNames;
import no.niths.domain.location.Location;

@XmlRootElement(name = AppNames.LOCATIONS)
@XmlAccessorType(XmlAccessType.FIELD)
public class LocationList extends ListAdapter<Location> {

	private static final long serialVersionUID = 7284616637714631167L;
	@XmlElement(name = "location")
	private List<Location> locationData;

	@Override
	public void setData(List<Location> locationData) {
		this.locationData = locationData;
	}

	public List<Location> getData() {
		return locationData;
	}

}

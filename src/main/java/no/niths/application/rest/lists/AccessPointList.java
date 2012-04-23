package no.niths.application.rest.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.constants.AppNames;
import no.niths.domain.signaling.AccessPoint;

@XmlRootElement(name = AppNames.ACCESS_POINTS)
public class AccessPointList extends ListAdapter<AccessPoint> {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    @XmlElement(name = "accesspoint")
    private List<AccessPoint> accessPointData;

    @Override
    public void setData(List<AccessPoint> accessPointData) {
        this.accessPointData = accessPointData;
    }
}
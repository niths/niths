package no.niths.application.rest.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;
import no.niths.domain.AccessPoint;

@XmlRootElement(name = AppConstants.ACCESS_POINTS)
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
package no.niths.application.rest.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;
import no.niths.domain.school.Locker;

@XmlRootElement(name = AppConstants.LOCKERS)
public class LockerList extends ListAdapter<Locker> {

    private static final long serialVersionUID = -4981503922392434483L;

    @SuppressWarnings("unused")
    @XmlElement(name = "locker")
    private List<Locker> lockerData;

    @Override
    public void setData(List<Locker> data) {
        this.lockerData = data;
    }
}
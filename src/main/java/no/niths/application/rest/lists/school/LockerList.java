package no.niths.application.rest.lists.school;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.constants.DomainConstantNames;
import no.niths.domain.school.Locker;
/**
 * Class to contain a list of lockers
 */
@XmlRootElement(name = DomainConstantNames.LOCKERS)
public class LockerList extends ListAdapter<Locker> {

    private static final long serialVersionUID = -4981503922392434483L;

    @SuppressWarnings("unused")
    @XmlElement(name = "locker")
    private List<Locker> data;

    @Override
    public void setData(List<Locker> data) {
        this.data = data;
    }
}
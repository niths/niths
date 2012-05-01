package no.niths.application.rest.lists.signaling;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.constants.DomainConstantNames;
import no.niths.domain.signaling.AccessField;

@XmlRootElement(name = DomainConstantNames.ACCESS_FIELDS)
public class AccessFieldList extends ListAdapter<AccessField> {

    private static final long serialVersionUID = -5120021637809836989L;

    @SuppressWarnings("unused")
    @XmlElement(name = "accessfield")
    private List<AccessField> accessFieldData;

    @Override
    public void setData(List<AccessField> list) {
        this.accessFieldData = list;
    }
}

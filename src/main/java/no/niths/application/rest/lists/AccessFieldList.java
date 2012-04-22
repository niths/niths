package no.niths.application.rest.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.constants.AppNames;
import no.niths.domain.signaling.AccessField;

@XmlRootElement(name = AppNames.ACCESS_FIELDS)
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

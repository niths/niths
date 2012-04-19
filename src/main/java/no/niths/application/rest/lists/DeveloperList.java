package no.niths.application.rest.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppNames;
import no.niths.domain.developing.Developer;

@XmlRootElement(name = AppNames.DEVELOPERS)
public class DeveloperList extends ListAdapter<Developer> {

    private static final long serialVersionUID = 7176343871162986392L;

    @SuppressWarnings("unused")
    @XmlElement(name = "developer")
    private List<Developer> data;

    @Override
    public void setData(List<Developer> data) {
        this.data = data;
    }
}
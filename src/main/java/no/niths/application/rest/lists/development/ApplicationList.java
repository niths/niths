package no.niths.application.rest.lists.development;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.constants.DomainConstantNames;
import no.niths.domain.development.Application;
/**
 * Class to contain a list of applications
 */
@XmlRootElement(name = DomainConstantNames.APPLICATIONS)
public class ApplicationList extends ListAdapter<Application> {

    private static final long serialVersionUID = 7176343871162986392L;

    @SuppressWarnings("unused")
    @XmlElement(name = "application")
    private List<Application> data;

    @Override
    public void setData(List<Application> data) {
        this.data = data;
    }
}
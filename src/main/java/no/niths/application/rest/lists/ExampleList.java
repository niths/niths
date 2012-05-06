package no.niths.application.rest.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.domain.ExampleDomain;
/**
 * Example list
 */
@XmlRootElement(name = "example")
public class ExampleList extends ListAdapter<ExampleDomain> {

    private static final long serialVersionUID = 2794068992188408350L;

    @SuppressWarnings("unused")
    @XmlElement(name = "example")
    private List<ExampleDomain> data;

    @Override
    public void setData(List<ExampleDomain> data) {
        this.data = data;
    }
}
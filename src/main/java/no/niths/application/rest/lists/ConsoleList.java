package no.niths.application.rest.lists;

import no.niths.common.AppConstants;
import no.niths.domain.Console;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = AppConstants.CONSOLES)
public class ConsoleList extends ListAdapter<Console> {

    private static final long serialVersionUID = 6293263726925010853L;
    @SuppressWarnings("unused")
    @XmlElement(name = "console")
    private List<Console> data;

    @Override
    public void setData(List<Console> consoles) {
        this.data = consoles;
    }
}
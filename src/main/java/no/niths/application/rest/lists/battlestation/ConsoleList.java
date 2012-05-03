package no.niths.application.rest.lists.battlestation;

import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.constants.DomainConstantNames;
import no.niths.domain.battlestation.Console;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
/**
 * Class to contain a list of consoles
 */
@XmlRootElement(name = DomainConstantNames.CONSOLES)
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
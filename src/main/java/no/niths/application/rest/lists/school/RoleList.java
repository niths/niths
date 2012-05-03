package no.niths.application.rest.lists.school;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.constants.DomainConstantNames;
import no.niths.domain.school.Role;
/**
 * Class to contain a list of roles
 */
@XmlRootElement(name = DomainConstantNames.ROLES)
public class RoleList extends ListAdapter<Role> {

    private static final long serialVersionUID = 7386282831236634626L;

    @SuppressWarnings("unused")
    @XmlElement(name = "role")
    private List<Role> data;

    @Override
    public void setData(List<Role> data) {
        this.data = data;
    }
}
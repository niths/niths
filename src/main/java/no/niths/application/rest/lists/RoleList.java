package no.niths.application.rest.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;
import no.niths.domain.security.Role;

@XmlRootElement(name = AppConstants.ROLES)
public class RoleList extends ListAdapter<Role>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 7386282831236634626L;
	@XmlElement(name = "role")
    private List<Role> data;

    @Override
    public void setData(List<Role> data) {
     this.data = data;
    }
}

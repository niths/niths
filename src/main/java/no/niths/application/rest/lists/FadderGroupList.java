package no.niths.application.rest.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;
import no.niths.domain.FadderGroup;
import no.niths.domain.Subject;

@XmlRootElement(name = AppConstants.FADDER_GROUP)
public class FadderGroupList extends ListAdapter<FadderGroup>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 7386282831236634626L;
	@XmlElement(name = "faddergruppe")
    private List<FadderGroup> data;

    @Override
    public void setData(List<FadderGroup> data) {
     this.data = data;
    }
}

package no.niths.application.rest.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;
import no.niths.domain.FadderGroup;

@XmlRootElement(name = AppConstants.FADDER)
public class FadderGroupList extends ListAdapter<FadderGroup>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 7386282831236634626L;
	@XmlElement(name = "fadderGroup")
    private List<FadderGroup> data;

    @Override
    public void setData(List<FadderGroup> data) {
     this.data = data;
    }
    
    public List<FadderGroup> getData(){
    	return data;
    }
}

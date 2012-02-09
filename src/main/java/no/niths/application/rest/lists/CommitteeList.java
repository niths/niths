package no.niths.application.rest.lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;
import no.niths.domain.Committee;

@XmlRootElement(name = AppConstants.COMMITTEES)
public class CommitteeList extends ArrayList<Committee>
        implements Exportable<Committee>{

    private static final long serialVersionUID = -7988084285022242469L;
    
    @XmlElement(name = "committee")
    private List<Committee> committeeData;

    @Override
    public void setData(List<Committee> committeeData) {
        this.committeeData = committeeData;
    }
    
    public List<Committee> getCourseData(){
    	return committeeData;
    }
    
}

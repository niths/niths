package no.niths.application.rest.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;
import no.niths.domain.CommitteeEvent;
import no.niths.domain.Event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@XmlRootElement(name = AppConstants.EVENTS)
@XmlAccessorType(XmlAccessType.FIELD)
public class CommitteeEventList extends ListAdapter<CommitteeEvent>{
    private static final long serialVersionUID = 3045223036129251886L;
    
    private static final Logger logger = LoggerFactory
			.getLogger(CommitteeEventList.class);
    
    @XmlElement
    private List<CommitteeEvent> eventData;

    @Override
    public void setData(List<CommitteeEvent> data) {
    	logger.info("Size is  " +data.size());
        this.eventData = data;
    }
    
    public List<CommitteeEvent> getData(){
    	return eventData;
    }
}

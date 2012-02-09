package no.niths.application.rest.lists;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;
import no.niths.domain.Course;
import no.niths.domain.Topic;

@XmlRootElement(name = AppConstants.TOPICS)
public class TopicList extends ArrayList<Topic>
        implements Exportable<Topic> {

    @XmlElement(name = "course")
    private List<Topic> data;

    @Override
    public void setData(List<Topic> data) {
     this.data = data;
    }
}

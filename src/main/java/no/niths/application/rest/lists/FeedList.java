package no.niths.application.rest.lists;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.constants.DomainConstantNames;
import no.niths.domain.school.Feed;

@XmlRootElement(name = DomainConstantNames.FEEDS)
public class FeedList extends ListAdapter<Feed> {

    private static final long serialVersionUID = -3367467710179177824L;

    @SuppressWarnings("unused")
    @XmlElement(name = "feed")
    private List<Feed> data;

    @Override
    public void setData(List<Feed> list) {
        this.data = list;
    }
}
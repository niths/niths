package no.niths.application.rest.lists.misc;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.application.rest.lists.ListAdapter;
import no.niths.common.constants.DomainConstantNames;
import no.niths.domain.misc.SocialLink;

@XmlRootElement(name = DomainConstantNames.SOCIAL_LINKS)
public class SocialLinkList extends ListAdapter<SocialLink> {

    private static final long serialVersionUID = -3897583397516840332L;

    @SuppressWarnings("unused")
    @XmlElement(name = "sociallink")
    private List<SocialLink> socialLinkData;

    @Override
    public void setData(List<SocialLink> socialLinkData) {
        this.socialLinkData = socialLinkData;
    }
}
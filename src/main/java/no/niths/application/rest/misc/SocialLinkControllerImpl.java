package no.niths.application.rest.misc;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.misc.SocialLinkList;
import no.niths.application.rest.misc.interfaces.SocialLinkController;
import no.niths.common.constants.DomainConstantNames;
import no.niths.domain.misc.SocialLink;
import no.niths.services.interfaces.GenericService;
import no.niths.services.misc.interfaces.SocialLinkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(DomainConstantNames.SOCIAL_LINKS)
public class SocialLinkControllerImpl
        extends    AbstractRESTControllerImpl<SocialLink>
        implements SocialLinkController {

    @Autowired
    private SocialLinkService service;

    private SocialLinkList socialLinkList = new SocialLinkList();

    @Override
    public GenericService<SocialLink> getService() {
        return service;
    }

    @Override
    public ListAdapter<SocialLink> getList() {
        return socialLinkList;
    }
}
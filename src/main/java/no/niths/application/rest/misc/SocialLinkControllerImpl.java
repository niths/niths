package no.niths.application.rest.misc;

import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.AbstractRESTControllerImpl;
import no.niths.application.rest.lists.ListAdapter;
import no.niths.application.rest.lists.misc.SocialLinkList;
import no.niths.application.rest.misc.interfaces.SocialLinkController;
import no.niths.common.constants.DomainConstantNames;
import no.niths.common.constants.SecurityConstants;
import no.niths.domain.misc.SocialLink;
import no.niths.services.interfaces.GenericService;
import no.niths.services.misc.interfaces.SocialLinkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    public void create(
            @RequestBody SocialLink socialLink,
            HttpServletResponse res) {
        super.create(socialLink, res);
    }

    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    public void update(@RequestBody SocialLink socialLink) {
        super.update(socialLink);
    }

    @Override
    @PreAuthorize(SecurityConstants.ADMIN_AND_SR)
    public void delete(@PathVariable long id) {
        super.delete(id);
    }
}
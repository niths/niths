package no.niths.services.misc;

import no.niths.domain.misc.SocialLink;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.misc.interfaces.SocialLinkRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.misc.interfaces.SocialLinkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialLinkServiceImpl extends AbstractGenericService<SocialLink>
        implements SocialLinkService {

    @Autowired
    private SocialLinkRepository repo;

    @Override
    public GenericRepository<SocialLink> getRepository() {
        return repo;
    }
}
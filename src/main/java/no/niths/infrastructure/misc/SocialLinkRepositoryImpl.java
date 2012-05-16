package no.niths.infrastructure.misc;

import no.niths.domain.misc.SocialLink;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.misc.interfaces.SocialLinkRepository;

import org.springframework.stereotype.Repository;

@Repository
public class SocialLinkRepositoryImpl
        extends AbstractGenericRepositoryImpl<SocialLink>
        implements SocialLinkRepository{

    public SocialLinkRepositoryImpl() {
        super(SocialLink.class, new SocialLink());
    }

}

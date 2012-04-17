package no.niths.infrastructure.signaling;

import no.niths.domain.signaling.AccessPoint;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.signaling.interfaces.AccessPointRepository;

import org.springframework.stereotype.Repository;

@Repository
public class AccessPointRepositoryImpl
        extends AbstractGenericRepositoryImpl<AccessPoint>
        implements AccessPointRepository {

    public AccessPointRepositoryImpl() {
        super(AccessPoint.class, new AccessPoint());
    }
}
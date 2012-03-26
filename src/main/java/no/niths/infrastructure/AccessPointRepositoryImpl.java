package no.niths.infrastructure;

import no.niths.domain.signaling.AccessPoint;
import no.niths.infrastructure.interfaces.AccessPointRepository;

import org.springframework.stereotype.Repository;

@Repository
public class AccessPointRepositoryImpl
        extends AbstractGenericRepositoryImpl<AccessPoint>
        implements AccessPointRepository {

    public AccessPointRepositoryImpl() {
        super(AccessPoint.class, new AccessPoint());
    }
}
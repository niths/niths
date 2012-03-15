package no.niths.infrastructure;

import no.niths.domain.signaling.AccessPoint;
import no.niths.infrastructure.interfaces.AccessPointRepository;

import org.springframework.stereotype.Repository;

@Repository
public class AccessPointRepositoryImpl
        extends AbstractGenericRepositoryImpl<AccessPoint>
        implements AccessPointRepository {

    public AccessPointRepositoryImpl() {
        super(AccessPoint.class);
    }

    @Override
    public void hibernateDelete(long id) {
        AccessPoint accessPoint = new AccessPoint();
        accessPoint.setId(id);
        getSession().getCurrentSession().delete(accessPoint);
    }
}
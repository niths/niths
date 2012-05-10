package no.niths.services.signaling;

import no.niths.common.helpers.ValidationHelper;
import no.niths.domain.signaling.AccessField;
import no.niths.domain.signaling.AccessPoint;
import no.niths.infrastructure.interfaces.GenericRepository;
import no.niths.infrastructure.signaling.interfaces.AccessFieldRepository;
import no.niths.infrastructure.signaling.interfaces.AccessPointRepository;
import no.niths.services.AbstractGenericService;
import no.niths.services.signaling.interfaces.AccessFieldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Service Class for AccessField
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for addAccessPoint
 * and removeAccessPoint
 * </p>
 */
@Service
public class AccessFieldServiceImpl extends AbstractGenericService<AccessField>
        implements AccessFieldService {

    @Autowired
    private AccessFieldRepository repo;

    @Autowired
    private AccessPointRepository accessPointRepo;

    @Override
    public GenericRepository<AccessField> getRepository() {
        return repo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAccessPoint(long afId, long apId) {
        AccessField accessField = validate(repo.getById(afId),
                AccessField.class);
        checkIfObjectExists(accessField.getAccessPoint(), apId,
                AccessPoint.class);

        AccessPoint accessPoint = accessPointRepo.getById(apId);
        ValidationHelper.isObjectNull(accessPoint, AccessPoint.class);
        accessField.setAccessPoint(accessPoint);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAccessPoint(long afId) {
        AccessField accessField = validate(repo.getById(afId),
                AccessField.class);
        boolean isRemoved = false;
        if (accessField.getAccessPoint() != null) {
            accessField.setAccessPoint(null);
            isRemoved = true;
        }
        checkIfIsRemoved(isRemoved, AccessPoint.class);
    }
}
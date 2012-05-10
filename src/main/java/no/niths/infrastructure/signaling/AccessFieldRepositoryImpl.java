package no.niths.infrastructure.signaling;

import no.niths.domain.signaling.AccessField;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.signaling.interfaces.AccessFieldRepository;

import org.springframework.stereotype.Repository;
/**
 * Repository class for AccessField
 *
 * <p>
 * Inherits the basic CRUD actions
 * </p>
 */
@Repository
public class AccessFieldRepositoryImpl extends
        AbstractGenericRepositoryImpl<AccessField> implements
        AccessFieldRepository {

    public AccessFieldRepositoryImpl() {
        super(AccessField.class, new AccessField());
    }
}

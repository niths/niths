package no.niths.infrastructure.school;

import no.niths.domain.school.Committee;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.school.interfaces.CommitteeRepositorty;

import org.springframework.stereotype.Repository;
/**
 * Repository class for Committee
 *
 * <p>
 * Inherits the basic CRUD actions
 * </p>
 */
@Repository
public class CommitteeRepositoryImpl extends AbstractGenericRepositoryImpl<Committee>
        implements CommitteeRepositorty{

    public CommitteeRepositoryImpl() {
        super(Committee.class, new Committee());
    }
    
}

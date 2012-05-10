package no.niths.infrastructure.school;

import no.niths.domain.school.Subject;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.school.interfaces.SubjectRepository;

import org.springframework.stereotype.Repository;
/**
 * Repository class for Subject
 *
 * <p>
 * Inherits the basic CRUD actions
 * </p>
 */
@Repository
public class SubjectRepositoryImpl extends AbstractGenericRepositoryImpl<Subject>
        implements SubjectRepository {

    public SubjectRepositoryImpl() {
        super(Subject.class, new Subject());
    }

}
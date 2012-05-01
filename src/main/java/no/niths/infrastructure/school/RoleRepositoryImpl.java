package no.niths.infrastructure.school;

import no.niths.domain.school.Role;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.school.interfaces.RoleRepository;

import org.springframework.stereotype.Repository;

/**
 * Repository class for Role
 *
 * <p>
 * Inherits the basic CRUD actions
 * </p>
 */
@Repository
public class RoleRepositoryImpl extends AbstractGenericRepositoryImpl<Role>
		implements RoleRepository{

	public RoleRepositoryImpl() {
		super(Role.class, new Role());
	}
}

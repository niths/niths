package no.niths.infrastructure.security;

import no.niths.domain.security.Role;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.interfaces.RoleRepository;

import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryImpl extends AbstractGenericRepositoryImpl<Role>
		implements RoleRepository{

	public RoleRepositoryImpl() {
		super(Role.class, new Role());
	}
}